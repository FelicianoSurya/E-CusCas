package id.ac.umn.e_cuscas.remote;

import id.ac.umn.e_cuscas.model.CatOrderHist;
import id.ac.umn.e_cuscas.model.Category;
import id.ac.umn.e_cuscas.model.JenisBarang;
import id.ac.umn.e_cuscas.model.ProdOrderHist;
import id.ac.umn.e_cuscas.model.Product;
import id.ac.umn.e_cuscas.model.ProductCheck;

public class JSONResponse {
    private Category[] dataCategpry;
    private Product[] dataProduct;
    private JenisBarang[] dataJenisBarang;
    private ProductCheck[] dataOrderDetail;
    private CatOrderHist[] dataOrder;
    private ProdOrderHist[] order_barang;


    public Category[] getDataCategory() {
        return dataCategpry;
    }
    public Product[] getDataProduct() { return dataProduct; }
    public JenisBarang[] getDataJenisBarang() { return dataJenisBarang; }
    public ProductCheck[] getDataDetProduct() { return dataOrderDetail; }
    public CatOrderHist[] getDataCatOrderHist() { return dataOrder; }
    public ProdOrderHist[] getDataProdOrderHist() { return order_barang; }
}
