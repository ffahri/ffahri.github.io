import java.util.Scanner;
import java.util.Hashtable;
import java.io.*;
public class labOdevMain 
{
	public static void main(String[] args) 
	{
		Scanner giris = new Scanner(System.in);
		String urunAdi;
		int stokAdedi;
		int alarmAdedi;
		int barkod;
		double fiyat;
		labYapi temp;
		System.out.println("Hashtable için ilk kapasiteyi girin Onerilen = 11 (tamsayı girin)");
		int tmp = giris.nextInt();
		System.out.println("Hashtable için load factor girin Onerilen = 0,75 (virgüllü girin");
		float lf = giris.nextFloat();
		Hashtable<Integer, labYapi> stok = new Hashtable<Integer, labYapi>(tmp,lf);	//integer ve sınıftan oluşan hashtable
		try
		{
		FileInputStream fileIn = new FileInputStream("hashtable.ser"); //dışarıdan alacağım dosyanın açılması için FileInputStream
   		ObjectInputStream in = new ObjectInputStream(fileIn);//dosyanın okunması için ObjectInputStream (seri okuma)
    	stok = (Hashtable<Integer, labYapi>)in.readObject(); // obje okumayla hashtablosuna ekle
    	}
    	catch(Exception a)
    	{
    	}		
		System.out.println("1-YENI URUN EKLE\n2-URUN SAT\n3-URUN DEGISTIR\n4-STOK EKLE\n5-URUNLERI LISTELE\n6-DISARI AKTAR\n-1-CIKIS");
		int secim = giris.nextInt();
		while(secim!=-1)
		{
			switch(secim)
			{
				case 1:
					urunAdi=giris.nextLine();
					System.out.println("Urun adini girin");
					urunAdi=giris.nextLine();
					System.out.println("Urunun stok adedini girin");
					stokAdedi=giris.nextInt();
					System.out.println("Urunun alarm adedini girin");
					alarmAdedi=giris.nextInt();
					System.out.println("Urunun fiyatini girin");
					fiyat = giris.nextDouble();
					System.out.println("Urunun barkodunu girin");
					barkod = giris.nextInt();
					stok.put(barkod,new labYapi(urunAdi,stokAdedi,alarmAdedi,fiyat));//hashtable'a ekleme metodu. barkod ve labYapi sınıfından bir nesne
				break;
				case 2:
					System.out.println("SATILACAK URUNUN BARKODUNU GIRIN");
					barkod = giris.nextInt();
					if(stok.containsKey(barkod)!=false)
					{
						temp=stok.get(barkod);//nesneyi temp adlı boş nesneye aktardım
						if(temp.satisYap()==true)//ve bu nesnenin içindeki metodu çalıştırdım
							System.out.println("BASARIYLA SATILDI");
						else
							System.out.println("SATIS BASARISIZ URUN STOĞU YETERSIZ");
						temp=null;
					}
					else
					System.out.println("BU BARKODA SAHIP URUN BULUNAMADI");
				break;
				case 3:
				System.out.println("DEGISTIRILECEK URUNUN BARKODUNU GIRIN");
				barkod = giris.nextInt();
				if(stok.containsKey(barkod)!=false)
				{
					temp = stok.get(barkod);
					System.out.println("URUNUN YENI ISMINI GIRIN");
					giris.nextLine();
					urunAdi = giris.nextLine();
					System.out.println("URUNUN YENI FIYATINI GIRIN");
					fiyat = giris.nextDouble();
					temp.setAdveFiyat(urunAdi,fiyat);
					System.out.println("BASARIYLA DEGISTIRLDI");
					temp=null;
				}
				else
					System.out.println("BU BARKODA SAHIP URUN BULUNAMADI");
				break;
				case 4:
				System.out.println("STOGA EKLENECEK URUNUN BARKODUNU GIRIN");
				barkod = giris.nextInt();
				if(stok.containsKey(barkod)!=false)
				{
					temp=stok.get(barkod);
					System.out.println("EKLENECEK URUN ADEDINI GIRIN");
					stokAdedi = giris.nextInt();
					temp.stokEkle(stokAdedi);
					System.out.println("BASARIYLA EKLENDI");
				}
				else
					System.out.println("BU BARKODA SAHIP URUN BULUNAMADI");
				break;
				case 5:
				System.out.println("TOPLAM "+labYapi.topAdet+" URUN VAR");
				for(int kod : stok.keySet())
					System.out.println("BARKOD = "+kod+" "+stok.get(kod));
				break;
				case 6:
					try
					{
					FileOutputStream fileOut = new FileOutputStream("hashtable.ser");//dosyayı açıyorum
    				ObjectOutputStream out = new ObjectOutputStream(fileOut);//dosyaya binary olarak yazıyorm
    				out.writeObject(stok);  				
    				System.out.println("BAŞARIYLA DIŞARIYA AKTARILDI (hashtable.ser)");
    				out.close();//dosyayı kapatıp bellekte yer açıyorum
    				fileOut.close();
    				}
    				catch(Exception a)
    				{
    				System.out.println("HATA DIŞARI AKTIRALAMADI");
    				}
				break;
			}
			System.out.println("1-YENI URUN EKLE\n2-URUN SAT\n3-URUN DEGISTIR\n4-STOK EKLE\n5-URUNLERI LISTELE\n6-DISARI AKTAR\n-1-CIKIS");
			secim = giris.nextInt();
		}
	}
}