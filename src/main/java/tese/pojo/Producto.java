package tese.pojo;
public class Producto {
    int Id;
    String Titulo;
    String Descripion;
    String urlImage;
    double precio;

    public Producto() {
    }

    public Producto(int Id, String Titulo, String Descripion, String urlImage, double precio) {
        this.Id = Id;
        this.Titulo = Titulo;
        this.Descripion = Descripion;
        this.urlImage = urlImage;
        this.precio = precio;
    }

  

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripion() {
        return Descripion;
    }

    public void setDescripion(String Descripion) {
        this.Descripion = Descripion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "Id=" + Id + ", Titulo=" + Titulo + ", Descripion=" + Descripion + ", urlImage=" + urlImage + ", precio=" + precio + '}';
    }
    
    
}
