package fr.epsi.mspr.restapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.amazonapi.FaceComparaison;
import fr.epsi.mspr.restapi.amazonapi.FaceRecoginition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceRecognitionTest {

//	@Test
//	public void apiRecognitionTest() {
//		String id1 = FaceRecoginition.getFaceId("http://img.shyndard.eu/BECHMHd.jpg");
//		System.out.println(id1);
//		String id2 = FaceRecoginition.getFaceId("http://img.shyndard.eu/MRV1Tdv.jpg");
//		System.out.println(id2);
//		double comparaison = FaceComparaison.compare(id1, id2);
//		System.out.println("Pourcentage de ressemblance : " + comparaison);
//	}
//	
//	@Test
//	public void apiComparaisonTest() {
//		FaceComparaison.compare(null, null);
//	}
}
