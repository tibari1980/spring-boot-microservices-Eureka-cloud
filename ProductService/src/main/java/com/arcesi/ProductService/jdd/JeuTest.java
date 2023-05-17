package com.arcesi.ProductService.jdd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JeuTest implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
    /**
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... args) throws Exception {
		CategoryEntity telephone=CategoryEntity.builder()
				.createdAt(Instant.now())
				.uidCategory(UUID.randomUUID().toString())
				.libelle("TELEPHONE")
				.description("Description telephone")
				.build();
		
		CategoryEntity ordinateur=CategoryEntity.builder()
				.createdAt(Instant.now())
				.uidCategory(UUID.randomUUID().toString())
				.libelle("ORDINATEUR PC")
				.description("Description ORDINATEUR")
				.build();
		CategoryEntity livre=CategoryEntity.builder()
				.createdAt(Instant.now())
				.uidCategory(UUID.randomUUID().toString())
				.libelle("LIVRE")
				.description("Description LIVRE")
				.build();
		
		CategoryEntity film=CategoryEntity.builder()
				.createdAt(Instant.now())
				.uidCategory(UUID.randomUUID().toString())
				.libelle("FILM")
				.description("Description FILM")
				.build();
		CategoryEntity catFil=categoryRepository.save(film);
		CategoryEntity catTel=categoryRepository.save(telephone);
		CategoryEntity catOrdi=categoryRepository.save(ordinateur);
		CategoryEntity cateLivre=categoryRepository.save(livre);
		
		for(int i=1;i<200;i++) {
			ProductEntity tele=ProductEntity.builder()
					.uidProduct(UUID.randomUUID().toString())
					.designation("IPHONE PRO"+i)
					.description("description iphone pro "+i)
					.prix((i%2!=0)? 640+i+9:800+10)
					.createdAt(Instant.now())
					.isDisponible(Boolean.TRUE)
					.isEnPromotion(Boolean.TRUE)
					.quantiteStock(i+1 *20+200)
					.categoryEntity(catTel)
					.photo("iphonepro"+i+".jpeg")
					.build();
			productRepository.save(tele);
			
			ProductEntity ord=ProductEntity.builder()
					.uidProduct(UUID.randomUUID().toString())
					.designation("ORDINATEUR MACBOOK PRO"+i)
					.description("description ORDINATEUR MAC BOOK pro "+i)
					.prix((i%2!=0)? 444+9:900+10)
					.createdAt(Instant.now())
					.isDisponible(Boolean.TRUE)
					.isEnPromotion(Boolean.TRUE)
					.quantiteStock(i+1 *20+20)
					.categoryEntity(catOrdi)
					.photo("ordinateurpro"+i+".jpeg")
					.build();
			productRepository.save(ord);
			
			ProductEntity liv=ProductEntity.builder()
					.uidProduct(UUID.randomUUID().toString())
					.designation("LIVRE CULTURE "+i)
					.description("description CULTURE "+i)
					.prix((i%2!=0)? 20+i+9:40+10+i)
					.createdAt(Instant.now())
					.isDisponible(Boolean.TRUE)
					.isEnPromotion(Boolean.TRUE)
					.quantiteStock(i+1 *20+200)
					.categoryEntity(cateLivre)
					.photo("livre"+i+".jpeg")
					.build();
			productRepository.save(liv);
			
			
			ProductEntity fil=ProductEntity.builder()
					.uidProduct(UUID.randomUUID().toString())
					.designation("FILM  "+i)
					.description("description FILM "+i)
					.prix((i%2!=0)? 30+i+9:45+10+i)
					.createdAt(Instant.now())
					.isDisponible(Boolean.TRUE)
					.isEnPromotion(Boolean.TRUE)
					.quantiteStock(i+1 *20+200)
					.categoryEntity(catFil)
					.photo("livre"+i+".jpeg")
					.build();
			productRepository.save(fil);
			
		}
	}

      */

	
}
