/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import java.util.ArrayList;
import java.util.List;
import static org.jocl.CL.*;
import org.jocl.Pointer;
import org.jocl.cl_device_id;
import org.jocl.cl_platform_id;

/**
 *
 * @author Tomasz
 */
public class ListUtil {
        
    public static void listPlatforms()
    {
        System.out.println("Listing all available platforms:");

        // Obtain the number of platforms
        int numPlatformsArray[] = new int[1];
        clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];
        
        System.out.printf("Available (%d) platforms:\n", numPlatforms);
        
        cl_platform_id platforms[] = new cl_platform_id[numPlatforms];
        clGetPlatformIDs(platforms.length, platforms, null);
        
        final int[] platformAttributes = 
        {
            CL_PLATFORM_NAME,
            CL_PLATFORM_VENDOR,  
            CL_PLATFORM_VERSION,
            CL_PLATFORM_EXTENSIONS,
            CL_PLATFORM_ICD_SUFFIX_KHR,
        };
        
        for(int i=0;i<platforms.length;i++)
        {
            System.out.println("--------Platform " + i + " --------");
            for(int pa: platformAttributes)
            {    
                long paramValueSizeArray[] = new long[1];
                clGetPlatformInfo(platforms[i], pa, 0, null, paramValueSizeArray);
                long paramValueSize = paramValueSizeArray[0];
                
                byte[] infoBuffer = new byte[(int)paramValueSize];
                clGetPlatformInfo(platforms[i], pa, paramValueSize,Pointer.to(infoBuffer), null);
                String info = new String(infoBuffer, 0, infoBuffer.length-1);
                
                System.out.println(info);
            }
            listDevices(platforms[i]);
        }
    }
    
    private static void listDevices(cl_platform_id platform)
    {  
        System.out.println("Listing all available devices for this platform:");
        final long deviceType = CL_DEVICE_TYPE_GPU;
        
        int numDevicesArray[] = new int[1];
        clGetDeviceIDs(platform, deviceType, 0, null, numDevicesArray);
        int numDevices = numDevicesArray[0];
        
        cl_device_id devices[] = new cl_device_id[numDevices];
        clGetDeviceIDs(platform, deviceType, numDevices, devices, null);
        
        System.out.println("---Devices---");
        
        for(int i=0;i < devices.length;i++)
        {
            long paramValueSizeArray[] = new long[1];
            clGetDeviceInfo(devices[i], CL_DEVICE_NAME, 0, null, paramValueSizeArray);
            long paramValueSize = paramValueSizeArray[0];
            
            byte[] infoBuffer = new byte[(int) paramValueSize];
            clGetDeviceInfo(devices[i], CL_DEVICE_NAME, paramValueSize, Pointer.to(infoBuffer),null);
            
            String info = new String(infoBuffer, 0, infoBuffer.length-1);
 
            System.out.println("--- Device "+i+" ---");
            System.out.println(info);
        }
    }
}
