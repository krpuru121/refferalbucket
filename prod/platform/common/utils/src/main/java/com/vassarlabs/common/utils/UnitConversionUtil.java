package com.vassarlabs.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.vassarlabs.common.utils.err.ObjectNotFoundException;

/**
 * This class converts measurements between different units. Currently supported units are 
 * m, cm, mm, ft
 * 
 * @author harsh
 *
 */
public class UnitConversionUtil {

	private static Map<Integer, Map<Integer, Double>> unitConversionMap = new HashMap<>();
	private static Map<String, Integer> unitMap = new HashMap<>();
	
	static {
		//km to other units
		Map<Integer, Double> kilometreConversionMap = new HashMap<>();
		kilometreConversionMap.put(UnitConstants.FEET_UNIT, 3280.84);
		kilometreConversionMap.put(UnitConstants.CENTIMETRE_UNIT, 100000.00);
		kilometreConversionMap.put(UnitConstants.MILLIMETRE_UNIT, 1000000.00);
		kilometreConversionMap.put(UnitConstants.METRE_UNIT, 1000.00);

		unitConversionMap.put(UnitConstants.KM_UNIT, kilometreConversionMap);
				
		//m to other units
		Map<Integer, Double> metreConversionMap = new HashMap<>();
		metreConversionMap.put(UnitConstants.FEET_UNIT, 3.28084);
		metreConversionMap.put(UnitConstants.CENTIMETRE_UNIT, 100.00);
		metreConversionMap.put(UnitConstants.MILLIMETRE_UNIT, 1000.00);
		metreConversionMap.put(UnitConstants.KM_UNIT, 0.001);
		
		unitConversionMap.put(UnitConstants.METRE_UNIT, metreConversionMap);
		
		//ft to other units
		Map<Integer, Double> feetConversionMap = new HashMap<>();
		feetConversionMap.put(UnitConstants.METRE_UNIT, 0.3048);
		feetConversionMap.put(UnitConstants.CENTIMETRE_UNIT, 30.48);
		feetConversionMap.put(UnitConstants.MILLIMETRE_UNIT, 304.8);
		feetConversionMap.put(UnitConstants.KM_UNIT, 0.0003048);
		
		unitConversionMap.put(UnitConstants.FEET_UNIT, feetConversionMap);
		
		//cm to other units
		Map<Integer, Double> centimetreConversionMap = new HashMap<>();
		centimetreConversionMap.put(UnitConstants.FEET_UNIT, 0.0328084);
		centimetreConversionMap.put(UnitConstants.METRE_UNIT, 0.01);
		centimetreConversionMap.put(UnitConstants.MILLIMETRE_UNIT, 10.00);
		centimetreConversionMap.put(UnitConstants.KM_UNIT, 0.00001);
		
		unitConversionMap.put(UnitConstants.CENTIMETRE_UNIT, centimetreConversionMap);
		
		//mm to other units
		Map<Integer, Double> millimetreConversionMap = new HashMap<>();
		millimetreConversionMap.put(UnitConstants.FEET_UNIT, 0.00328084);
		millimetreConversionMap.put(UnitConstants.METRE_UNIT, 0.001);
		millimetreConversionMap.put(UnitConstants.CENTIMETRE_UNIT, 0.1);
		millimetreConversionMap.put(UnitConstants.KM_UNIT, 0.000001);

		unitConversionMap.put(UnitConstants.MILLIMETRE_UNIT, millimetreConversionMap);
		
		//cubic km to other units
		Map<Integer, Double> cubicKMConversionMap = new HashMap<>();
		cubicKMConversionMap.put(UnitConstants.TMC_UNIT, 35.314666721);
		
		unitConversionMap.put(UnitConstants.CUBIC_KM_UNIT, cubicKMConversionMap);

		//mcft to other units
		Map<Integer, Double> mcftConversionMap = new HashMap<>();
		mcftConversionMap.put(UnitConstants.TMC_UNIT, 0.001);
		
		unitConversionMap.put(UnitConstants.MCFT_UNIT, mcftConversionMap);

		//cft to other units
		Map<Integer, Double> cftConversionMap = new HashMap<>();
		cftConversionMap.put(UnitConstants.TMC_UNIT, 0.000000001);
		cftConversionMap.put(UnitConstants.CUBIC_METRE_UNIT, 0.028316847);

		unitConversionMap.put(UnitConstants.CUBIC_FEET_UNIT, cftConversionMap);

		//cubic m to other units
		Map<Integer, Double> cubicMConversionMap = new HashMap<>();
		cubicMConversionMap.put(UnitConstants.CUBIC_FEET_UNIT, 35.314666721);

		unitConversionMap.put(UnitConstants.CUBIC_METRE_UNIT, cubicMConversionMap);

		//Added for Jira : IWM -391 Send daily sms
		//Need to convert Cusecs to Mcft
		
		//cusec to other units
		Map<Integer, Double> cusecConversionMap = new HashMap<>();
		cusecConversionMap.put(UnitConstants.MCFT_UNIT, 0.000001);

		unitConversionMap.put(UnitConstants.CUSEC_UNIT, cusecConversionMap);

		
		unitMap.put(UnitConstants.MILLIMETRE_UNIT_TEXT, UnitConstants.MILLIMETRE_UNIT);
		unitMap.put(UnitConstants.CENTIMETRE_UNIT_TEXT, UnitConstants.CENTIMETRE_UNIT);
		unitMap.put(UnitConstants.METRE_UNIT_TEXT, UnitConstants.METRE_UNIT);
		unitMap.put(UnitConstants.FEET_UNIT_TEXT, UnitConstants.FEET_UNIT);
		unitMap.put(UnitConstants.CUSEC_UNIT_TEXT, UnitConstants.CUSEC_UNIT);
		unitMap.put(UnitConstants.TMC_UNIT_TEXT, UnitConstants.TMC_UNIT);
	
	}

	/**
	 * This method returns the multiplier to convert value in "unit" to "targetUnit"
	 * 
	 * 1 targetUnit = multiplier * 1 unit
	 * 
	 * @param unit
	 * @param targetUnit
	 * @return multiplier
	 */
	public static Double getConversionMultiplier(Integer unit, Integer targetUnit) {
		Map<Integer, Double> unitConversions = unitConversionMap.get(unit);
		if(unitConversions == null) {
			System.out.println("UnitConversionUtil: unit " + unit + " is not supported");
			return null;
		}
		Double multiplier = unitConversions.get(targetUnit);
		if(multiplier == null) {
			System.out.println("UnitConversionUtil: target unit " + targetUnit + " not supported for " + unit);
			return null;
		}
		
		return multiplier;
	}
	
	
	public static int getUnitConstant(String unitText) throws ObjectNotFoundException{
		return unitMap.get(unitText);
	}
}
