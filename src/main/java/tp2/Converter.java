package tp2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter est une classe  qui convertie les donnees (size, date,...)
 * dans un format lisible.
 */
public final class Converter {
	
	/**
	 * Convertie à partir de la taille en à un format approprie (o, Ko, Mo, Go).
	 * @param size en octets
	 * @return string qui contiensla taille convertie avec le format approprie.
	 */
	public static String getConvertedSize(long size) {
		
		DecimalFormat result = new DecimalFormat("0.00");
		
		// Taille negative
		if (size < 0) {
			return "SIZE_ERROR";
		// Taille en octets
		}else if (size < 1024) {
			return size + " octets";
		// Taille en Ko
		}else if ((size/1024) < 1024) {
			return result.format(((double)size/1024)) + " Ko";
		// Taille en Mo
		}else if ((size/1048576) < 1024) {
			return result.format(((double)size/1048576)) + " Mo";
		// Taille en Go
		}else{
			return result.format(((double)size/1073741824)) + " Go";
		}

	}
	
	/**
	 * Convertie au format français (DD/MM/YYYY HH:MM:SS).
	 * @param date à convertir
	 * @return un string au format français.
	 */
	public static String getConvertedDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return df.format(date);
	}

}
