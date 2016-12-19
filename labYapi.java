public class labYapi implements java.io.Serializable // serializable arayüzünü alıyorum
{
	private String urunAdi;
	private int stokAdedi;
	private int alarmAdedi;
	private double fiyat;
	public static int topAdet = 0;
	public labYapi(String urunAdi,int stokAdedi,int alarmAdedi,double fiyat)
	{	//kurucu metodum
		this.urunAdi = urunAdi;
		this.stokAdedi = stokAdedi;
		this.alarmAdedi = alarmAdedi;
		this.fiyat = fiyat;
		topAdet++;
	}
	public String getUrunAdi()
	{
		return urunAdi;
	}
	public int alarmAdedi()
	{
		return alarmAdedi;
	}
	public String toString()
	{
		return String.format(" URUN ADI = "+urunAdi+" STOK ADEDI = "+stokAdedi+" ALARM ADEDI = "+alarmAdedi+" FIYAT = "+fiyat+"TL");
	}
	public boolean satisYap()
	{
		if(stokAdedi>0)
		{
			--stokAdedi;
			alarmKontrol();
			return true;
		}
		else
			return false;
	}
	public void alarmKontrol()
	{
		if(stokAdedi<=alarmAdedi)
			System.out.println("URUN STOĞU KRITIK DURUMDA ! STOĞA URUN EKLEYIN");
	}
	public void setAdveFiyat(String urunAdi,double fiyat)
	{
		this.urunAdi=urunAdi;
		this.fiyat=fiyat;
	}
	public void stokEkle(int ek)
	{
		this.stokAdedi+=ek;
	}
}