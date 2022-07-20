package br.com.bd2.javaspringbd2museu.model;

public class ArtObject {
  private long numid;
  private String nomeArtista;
  private String periodo;
  private int ano;
  private String titulo;
  private String descricao;
  private String cultura;
  private String estilo;
  private float custo;
  private String tipo;
  private String dataCompra;
  private String nomeColecao;
  private int quantidade;

  public ArtObject() {
  }

  public ArtObject(
    String nomeArtista, 
    String periodo, 
    int ano, 
    String titulo, 
    String descricao,
    String cultura,
    String estilo,
    float custo,
    String tipo
  ) {
    this.nomeArtista = nomeArtista;
    this.periodo = periodo;
    this.ano = ano;
    this.titulo = titulo;
    this.descricao = descricao;
    this.cultura = cultura;
    this.estilo = estilo;
    this.custo = custo;
    this.tipo = tipo;
  }

  public String getNomeColecao() {
      return nomeColecao;
  }

  public void setNomeColecao(String nomeColecao) {
      this.nomeColecao = nomeColecao;
  }

  public int getQuantidade() {
      return quantidade;
  }

  public void setQuantidade(int quantidade) {
      this.quantidade = quantidade;
  }

  public String getDataCompra() {
      return dataCompra;
  }

  public void setDataCompra(String dataCompra) {
      this.dataCompra = dataCompra;
  }
  
  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getCultura() {
    return cultura;
  }

  public void setCultura(String cultura) {
    this.cultura = cultura;
  }

  public float getCusto() {
    return custo;
  }

  public void setCusto(float custo) {
    this.custo = custo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getEstilo() {
    return estilo;
  }

  public void setEstilo(String estilo) {
    this.estilo = estilo;
  }

  public String getNomeArtista() {
    return nomeArtista;
  }

  public void setNomeArtista(String nomeArtista) {
    this.nomeArtista = nomeArtista;
  }

  public long getNumid() {
    return numid;
  }

  public void setNumid(long numid) {
    this.numid = numid;
  }

  public String getPeriodo() {
    return periodo;
  }

  public void setPeriodo(String periodo) {
    this.periodo = periodo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
}
