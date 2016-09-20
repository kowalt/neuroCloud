/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojol.original;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Tomasz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Layer 
{
    @XmlAttribute
    private int id;
    private int relative_number;
    @XmlElementWrapper
    @XmlElement(name="neuron")
    private List<Neuron> neurons = new ArrayList<>();
    @XmlTransient
    private Network network;
    
    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelative_number() {
        return relative_number;
    }

    public void setRelative_number(int relative_number) {
        this.relative_number = relative_number;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
