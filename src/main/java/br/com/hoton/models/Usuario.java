package br.com.hoton.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String password;
    private String clientId;
    private String clientSecret;
    private String basic;
    private boolean enabled;
    private boolean setup;
    private boolean firstAcess;
    private String hotonTok;
    private String hotmartToken;
    private String monetizzeToken;
    private String eduzzToken;
    private String telefone;
    private boolean cancelamentoServico;
    private String codAssinante;
    
    public Usuario() {}
    
    public Usuario(Long id, String email, String hotmartToken, String hotonTok, String monetizzeToken, String eduzzToken, String nome, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.hotmartToken = hotmartToken;
		this.hotonTok = hotonTok;
		this.nome = nome;
		this.monetizzeToken = monetizzeToken;
		this.eduzzToken = eduzzToken;
		this.roles = roles;
	}

	public Usuario(Long id) {
		this.id = id;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy="usuario")
    private Set<Venda> vendas;
    
    @OneToMany(mappedBy="usuario")
    private Set<Mensagem> mensagens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotonTok() {
		return hotonTok;
	}

	public void setHotonTok(String hotonTok) {
		this.hotonTok = hotonTok;
	}

	public boolean isSetup() {
		return setup;
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}

	public boolean isFirstAcess() {
		return firstAcess;
	}

	public void setFirstAcess(boolean firstAcess) {
		this.firstAcess = firstAcess;
	}

	public String getEmail() {
		return email.toUpperCase();
	}

	public void setEmail(String email) {
		this.email = email.toUpperCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getBasic() {
		return basic;
	}

	public String getHotmartToken() {
		return hotmartToken;
	}

	public void setHotmartToken(String hotmartToken) {
		this.hotmartToken = hotmartToken;
	}

	public Set<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Set<Venda> vendas) {
		this.vendas = vendas;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public Set<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(Set<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isCancelamentoServico() {
		return cancelamentoServico;
	}

	public void setCancelamentoServico(boolean cancelamentoServico) {
		this.cancelamentoServico = cancelamentoServico;
	}

	public String getCodAssinante() {
		return codAssinante;
	}

	public void setCodAssinante(String codAssinante) {
		this.codAssinante = codAssinante;
	}

	public String getMonetizzeToken() {
		return monetizzeToken;
	}

	public void setMonetizzeToken(String monetizzeToken) {
		this.monetizzeToken = monetizzeToken;
	}

	public String getEduzzToken() {
		return eduzzToken;
	}

	public void setEduzzToken(String eduzzToken) {
		this.eduzzToken = eduzzToken;
	}
}
