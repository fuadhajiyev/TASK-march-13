package az.company.training.service;

import az.company.training.dto.request.ProductCreateRequest;
import az.company.training.dto.response.ProductResponse;
import az.company.training.entity.Product;
import az.company.training.mapper.ProductMapper;
import az.company.training.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // CREATE: Yeni bir resurs yaradırıq
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        // 1. TRANSIENT 🌱
        // mapToEntity obyekt yaradır (new Product()). 
        // ID yoxdur, Hibernate hələ bu obyekti tanımır.
        Product product = productMapper.mapToEntity(request);

        // 2. PERSISTENT (MANAGED) 🔗
        // save() metodu obyekti Hibernate-in "Persistence Context"-inə daxil edir.
        // İndi obyektin ID-si var və hər dəyişiklik izlənilir.
        Product savedProduct = productRepository.save(product);

        return productMapper.mapToResponse(savedProduct);
    } // Metod bitir -> Transaction COMMIT -> Obyekt DETACHED olur.

    // GET BY ID: Mövcud resursu gətiririk
    public ProductResponse getProductById(Long id) {
        // PERSISTENT (MANAGED) 🔗
        // findById() bazadan məlumatı gətirir və onu dərhal "izləməyə" alır.
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Məhsul tapılmadı: " + id));

        return productMapper.mapToResponse(product);
    }

    // GET ALL
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE: Məlumatı yeniləyirik (Ən kritik Lifecycle hissəsi)
    @Transactional
    public ProductResponse updateProduct(Long id, ProductCreateRequest request) {
        // 1. PERSISTENT 🔗
        // Obyekt bazadan tapılır və Hibernate onu izləməyə başlayır.
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Məhsul tapılmadı"));

        // 2. DIRTY CHECKING (Sehirli hissə) ✨
        // Obyekt "Persistent" halda olduğu üçün setterləri çağırmaq kifayətdir.
        // Hibernate arxa planda yaddaşdakı obyektin "çirkləndiyini" görür.
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        // save() çağırmağa ehtiyac yoxdur! 
        return productMapper.mapToResponse(product);
    } // Metod bitəndə Hibernate avtomatik UPDATE sorğusu göndərir.

    // DELETE: Resursu silirik
    @Transactional
    public void deleteProduct(Long id) {
        // 1. PERSISTENT 🔗
        // Əvvəlcə obyekti tapıb Managed hala gətiririk (və ya birbaşa ID ilə silirik)
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Məhsul tapılmadı"));

        // 2. REMOVED 🗑️
        // delete() obyekti silinməyə işarələyir. 
        productRepository.delete(product);
    } // Metod bitəndə SQL DELETE sorğusu icra olunur.


}