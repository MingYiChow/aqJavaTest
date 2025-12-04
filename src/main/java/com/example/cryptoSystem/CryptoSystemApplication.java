package com.example.cryptoSystem;

import com.example.cryptoSystem.entity.Wallet;
import com.example.cryptoSystem.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoSystemApplication {
    private final WalletRepository walletRepository;

    public CryptoSystemApplication(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(CryptoSystemApplication.class, args);
	}

    @Bean
    CommandLineRunner init(WalletRepository walletRepository) {
        return args -> {
            walletRepository.save(new Wallet("Adam", "adam@gmail.com", "USDT", "50000"));
        };
    }

}
