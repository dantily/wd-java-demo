import com.sohu.game.ip.util.IllegalIpException;
import com.sohu.game.ip.util.IpSeeker;
import com.sohu.game.ip.util.IpValue;


/**
 * 
 */

/**
 * @author wudan_js
 *
 */
public class IpSeekerDemo {
	public static void main(String[] args) throws IllegalIpException {
		IpSeeker ipSeeker=IpSeeker.getInstance();
		IpValue address=ipSeeker.locate("183.139.213.94");
		System.out.println(address.getArea());
		System.out.println(address.getCountry());
		System.out.println(address.getProvince());
		System.out.println(address.getCity());
	}
}
