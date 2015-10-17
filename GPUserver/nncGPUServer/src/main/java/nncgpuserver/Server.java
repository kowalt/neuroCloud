/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import nncgpuserver.model.Network;
import org.jocl.cl_kernel;
import org.jocl.cl_mem;
import static org.jocl.CL.*;
import org.jocl.Sizeof;
import org.jocl.cl_context;

/**
 *
 * @author Tomasz
 */
public class Server implements RunNetwork
{    
    private DeviceInitializer initializer;

    public DeviceInitializer getInitializer() {
        return initializer;
    }

    public void setInitializer(DeviceInitializer initializer) {
        this.initializer = initializer;
    }
    
    @Override
    public int[] run(int[] input) 
    {
        cl_kernel kernel_input = initializer.getKernel_input();
        cl_kernel kernel_output = initializer.getKernel_output();
        
        
        
        return null;
    }

    @Override
    public void loadNetworkIntoGPU(Network n) {
        cl_context context = initializer.getContext();
        
        n = NetworkUtil.convert();
        
        
        cl_mem inputWeightsMem[] = new cl_mem[3];
        memObjects[0] = clCreateBuffer(context, 
        CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
        Sizeof.cl_float * n, srcA, null);
    }
}
