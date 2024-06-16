package service.Impl;

import exception.EcommerceException;
import model.ErrorCode;
import model.Product;
import repository.ProductRepository;
import service.ProductService;
import utils.ErrorCodeMap;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public String addProduct(Product product) throws EcommerceException {
         Product createdProduct = productRepository.createProduct(product);
         return createdProduct.getProductId();
    }

    @Override
    public Product getProduct(String productId) throws EcommerceException {
        return productRepository.getProduct(productId);
    }
    @Override
    public boolean checkInventory(int orderedQuantity, String productId){
        synchronized (this) {
            Product product = productRepository.getProduct(productId);
            if (orderedQuantity <= product.getProductQuantity()) {
                product.setProductQuantity(product.getProductQuantity() - orderedQuantity);
                return true;
            } else {
                throw new EcommerceException(ErrorCode.IN_SUFFICIENT_INVENTORY, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.IN_SUFFICIENT_INVENTORY));
            }
        }
    }
}
