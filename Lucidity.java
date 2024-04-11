import java.util.HashMap;
import java.util.Map;

public class DeliveryOptimizer {
    
    private static final double AVERAGE_SPEED = 20.0;
    
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c; 
    }
    
    public static double calculateDeliveryTime(double distance, double preparationTime) {
        return (distance / AVERAGE_SPEED) + preparationTime;
    }
    
    public static String findOptimalPath(Map<String, double[]> locations, Map<String, Double> preparationTimes) {
        double totalDeliveryTimeFromR1 = calculateDeliveryTime(
            calculateDistance(locations.get("C1")[0], locations.get("C1")[1], locations.get("R1")[0], locations.get("R1")[1]),
            preparationTimes.get("R1")
        ) + calculateDeliveryTime(
            calculateDistance(locations.get("C2")[0], locations.get("C2")[1], locations.get("R1")[0], locations.get("R1")[1]),
            preparationTimes.get("R1")
        );
        
        double totalDeliveryTimeFromR2 = calculateDeliveryTime(
            calculateDistance(locations.get("C1")[0], locations.get("C1")[1], locations.get("R2")[0], locations.get("R2")[1]),
            preparationTimes.get("R2")
        ) + calculateDeliveryTime(
            calculateDistance(locations.get("C2")[0], locations.get("C2")[1], locations.get("R2")[0], locations.get("R2")[1]),
            preparationTimes.get("R2")
        );
        
        if (totalDeliveryTimeFromR1 < totalDeliveryTimeFromR2) {
            return "Optimal path: R1 -> C1, R1 -> C2";
        } else {
            return "Optimal path: R2 -> C1, R2 -> C2";
        }
    }
}
