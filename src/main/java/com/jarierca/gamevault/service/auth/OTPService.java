package com.jarierca.gamevault.service.auth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OTPService {

	private static final Duration TIME_STEP = Duration.ofSeconds(30);
	private final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(TIME_STEP);

	public String generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
		keyGenerator.init(160); // 160 bits for SHA-1
		Key key = keyGenerator.generateKey();
		Base32 base32 = new Base32();
		return base32.encodeToString(key.getEncoded());
	}

	public String generateOTP(String base32Secret) throws Exception {
		Key secretKey = getKeyFromBase32(base32Secret);
		Instant timeToCheck = Instant.now();
		return String.valueOf(totp.generateOneTimePassword(secretKey, timeToCheck));
	}

	public boolean verifyOTP(String base32Secret, String otp) throws Exception {
		Key secretKey = getKeyFromBase32(base32Secret);
		Instant timeToCheck = Instant.now();
		String generatedOTP = String.valueOf(totp.generateOneTimePassword(secretKey, timeToCheck));
		return otp.equals(generatedOTP);
	}

	private Key getKeyFromBase32(String base32Secret) {
		Base32 base32 = new Base32();
		byte[] decodedKey = base32.decode(base32Secret);
		return new SecretKeySpec(decodedKey, totp.getAlgorithm());
	}

	public String generateQrCodeDataUrl(String username, String base32Secret) throws Exception {
		String otpauthUrl = String.format("otpauth://totp/%s?secret=%s&issuer=%s",
				URLEncoder.encode("GameVault:" + username, StandardCharsets.UTF_8.toString()), base32Secret,
				URLEncoder.encode("GameVault", StandardCharsets.UTF_8.toString()));

		return otpauthUrl;
	}
}
