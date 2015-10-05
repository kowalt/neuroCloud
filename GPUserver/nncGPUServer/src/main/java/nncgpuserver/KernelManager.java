/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

/**
 *
 * @author Tomasz
 */
public class KernelManager 
{
    private String kernelSource;

    public String getKernelSource() {
        return kernelSource;
    }

    public void setKernelSource(String kernelSource) {
        this.kernelSource = kernelSource;
    }

    public void createKernelCode(String activation_function)
    {
        activation_function = activation_function.replaceAll("x", "input[gid]");
        
        this.kernelSource = "__kernel float "+
        "calculateNeuronOutputKernel(__global const float *input)" +
        "{"+
        "    int gid = get_global_id(0);"+
        "    return " + activation_function + ";" +   
        "}";
    }
}
