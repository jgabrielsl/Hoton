package br.com.hoton.components;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.hoton.models.Disparador;
import br.com.hoton.models.enums.DisparadorStatus;
import br.com.hoton.models.whatsapp.Contato;
import br.com.hoton.models.whatsapp.ContatoStatus;
import br.com.hoton.repository.ConfRepo;
import br.com.hoton.repository.ContatoRepo;
import br.com.hoton.repository.DisparadorRepo;

@Component
@EnableScheduling
public class DisparadorScheduled {
	
	@Autowired
	private DisparadorRepo dispRepo;
	
	@Autowired
	private ContatoRepo contRepo;
	
	@Autowired
	private ConfRepo confRepo;
	
	@Autowired
	private Environment env;
	
	
	@Scheduled(fixedDelay = 60000*60)
	@Async
	public void processaDisparador() {
		List<Disparador> lista = dispRepo.findAllByStatus(DisparadorStatus.PARA_PROCESSAMENTO);
		
		if(lista.size() > 0) {
			lista.forEach(disp ->{
				disp.setStatus(DisparadorStatus.PROCESSANDO);
				disp = dispRepo.save(disp);
				DisparadorStatus status = DisparadorStatus.PROCESSADO_SUCCESSO;
				try {
					FileInputStream file = new FileInputStream(new File(env.getProperty("disparo")+disp.getFileName()));
					
					XSSFWorkbook workbook = new XSSFWorkbook(file);
			        XSSFSheet sheet = workbook.getSheetAt(0);
			        
			        for (int i = 0; i < sheet.getLastRowNum(); i++) {
			        	if(i > 99)
			        		break;
			        	try {
							XSSFRow row = sheet.getRow(i);
							
							if(row == null)
								continue;		
							
							Contato cont = new Contato();
							cont.setConf(confRepo.findByUsuario(disp.getUser()));
							cont.setDisparador(disp);
							cont.setStatus(ContatoStatus.AGUARDANDO_ENVIO);
							if(disp.isProcessed())
								cont.setTimes(disp.getDtDisparo());
							else
								cont.setTimes(LocalDateTime.now());
							try {
								cont.setTelefone(getCellValue(row.getCell(0)));
								cont.setMensagemSend(getCellValue(row.getCell(1)));
								contRepo.save(cont);
							}catch (Exception e) {
								e.printStackTrace();
								status = DisparadorStatus.PROCESSADO_ERRO;
							}
			        	}catch (Exception e) {
			        		e.printStackTrace();
			        		status = DisparadorStatus.PROCESSADO_ERRO;
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					status = DisparadorStatus.PROCESSADO_ERRO;
				}
				disp.setStatus(status);
				dispRepo.save(disp);
			});				
		}
	}
	
	private String getCellValue(XSSFCell cell) throws Exception {		
		if(cell == null)
			throw new Exception("Célula vazia");
		String content = "";
		if(cell.getCellType() == CellType.STRING)
			content = cell.getStringCellValue();
		
		if(cell.getCellType() == CellType.NUMERIC) {
			Double val = cell.getNumericCellValue();
			content = val.toString();
		}
			
		return content;	
	}
}

