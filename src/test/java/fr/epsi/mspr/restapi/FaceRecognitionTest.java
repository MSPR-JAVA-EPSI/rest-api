package fr.epsi.mspr.restapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.amazonapi.FaceComparaison;
import fr.epsi.mspr.restapi.amazonapi.FaceRecoginition;
import fr.epsi.mspr.restapi.amazonapi.FaceRecoginitionStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceRecognitionTest {
//
//	@Test
//	public void apiRecognitionTest() {
//		try {
//			byte[] img = Files.readAllBytes(Paths.get("C:\\Users\\Mael\\Desktop\\BECHMHd.jpg"));
//			String id1 = FaceRecoginitionStream.getFaceId(img);
//			System.out.println(id1);
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void apiRecognitionUrlTest() {
//		String id1 = FaceRecoginition.getFaceId("http://img.shyndard.eu/BECHMHd.jpg");
//		System.out.println(id1);
//		String id2 = FaceRecoginition.getFaceId("http://img.shyndard.eu/Aj4Vj8F.jpg");
//		//String id2 = FaceRecoginition.getFaceId("http://img.shyndard.eu/MRV1Tdv.jpg");
//		System.out.println(id2);
//		boolean comparaison = FaceComparaison.compare(id1, id2);
//		System.out.println("Pourcentage de ressemblance : " + comparaison);
//	}
//	
//	@Test
//	public void apiComparaisonTest() {
//		FaceComparaison.compare(null, null);
//	}
}