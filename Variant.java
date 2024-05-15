/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nch.igm.labvantage.util;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import sapphire.SapphireException;
import sapphire.xml.PropertyList;
import sapphire.SapphireException;
import sapphire.accessor.ActionException;
import sapphire.accessor.ActionProcessor;
import sapphire.action.AddDataSet;
import sapphire.action.AddSDI;
import sapphire.action.AddSDIAttribute;
import sapphire.action.AddSDIDetail;
import sapphire.action.BaseAction;
import sapphire.action.EditSDI;
import sapphire.action.EditSDIDetail;
import sapphire.action.EditTrackItem;
import sapphire.action.EnterDataSet;
import sapphire.util.DataSet;
import sapphire.xml.PropertyList;

import sapphire.SapphireException;
import sapphire.accessor.ActionException;
import sapphire.accessor.ActionProcessor;
import sapphire.action.AddDataSet;
import sapphire.action.AddSDI;
import sapphire.action.AddSDIAttribute;
import sapphire.action.AddSDIDetail;
import sapphire.action.BaseAction;
import sapphire.action.EditSDI;
import sapphire.action.EditSDIDetail;
import sapphire.action.EditTrackItem;
import sapphire.action.EnterDataSet;
import sapphire.util.DataSet;
import sapphire.xml.PropertyList;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author spm006
 */
public class Variant {
    
    public class GermlineSequence extends Variant{
        public HashMap<String,String> GermlineSequence(JSONObject variantObj) throws JSONException {
        
            HashMap<String,String> VariantMap = new HashMap<String, String>();
            
            String geneSymbol;
            String transcriptId;
            String chromosome;
            Long position;
            String ref;
            String alt;
            Float vaf;
            String genomicChange;
            String codingChange;
            String nucleotideChange;
            String probandZygosity;
            String zygosity;
            String proteinChange;
            String interpTemp;
            String variantInfo;
            String classification;
            String reportTableType;
            String entrezId;
            HashMap<String,String> AssocConditions = new HashMap<String,String>();
            JSONArray diseaseAssocConditions;
            
          
            geneSymbol = variantObj.getString("geneSymbol");
            transcriptId = variantObj.getString("transcriptId");
            codingChange = variantObj.getString("codingChange");
            proteinChange = variantObj.isNull("proteinChange") ? "p.?" : variantObj.getString("proteinChange").replaceAll("\\*", "Ter");
            chromosome = variantObj.getString("chromosome");
            classification = variantObj.getString("classification");
            position = (Long) variantObj.get("position");
            ref = variantObj.getString("ref");
            alt = variantObj.getString("alt");
            probandZygosity = variantObj.has("probandZygosity") ? StringUtils.capitalise(variantObj.getString("probandZygosity")) : "";
            vaf  = (Float) variantObj.get("vaf");
            entrezId = variantObj.getString("entrezId");
            diseaseAssocConditions = variantObj.getJSONArray("diseaseAssociatedConditions");
            
            for (int i=0; i < diseaseAssocConditions.length(); i++){

                String phenInheritance = diseaseAssocConditions.getJSONObject(i).isNull("phenotypeInheritance") ? "" : diseaseAssocConditions.getJSONObject(i).getString("phenotypeInheritance");
                if(!phenInheritance.isEmpty()){
                    String phen = diseaseAssocConditions.getJSONObject(i).getString("phenotype");
                    Integer mimNum = diseaseAssocConditions.getJSONObject(i).getInt("phenotypejMimNumber");
                }
                
                
            }
            
                
             
            return VariantMap;
        }
    }
    
    public class SomaticSequence extends Variant{
        public HashMap<String,String> SomaticSequence(JSONObject variantObj){
            HashMap<String,String> VariantMap = new HashMap<String, String>();
             
            return VariantMap;
        }
    }
    
    public class GermlineCNV extends Variant{
        public HashMap<String,String> GermlineCNV(JSONObject variantObj){
            
            HashMap<String,String> VariantMap = new HashMap<String, String>();
             
            return VariantMap;
            
        }
    }
    
    public class SomaticCNV extends Variant{
        public HashMap<String,String> SomaticCNV(JSONObject variantObj){
            HashMap<String,String> VariantMap = new HashMap<String, String>();
             
            return VariantMap;
        }
    }
    //implement in Varhouse Import
    public String AddVariant(HashMap<String,String> VariantMap) throws SapphireException {
    
        try {
            PropertyList addSDIprops = new PropertyList();
            addSDIprops.setProperty("sdcid","ResultEntryTables");
            addSDIprops.setProperty("paramtype","table");
            
           for (String key : VariantMap.keySet()){
                String value = VariantMap.get(key);
              addSDIprops.setProperty(key,value);  
            }
            getActionProcessor().processAction("AddSDI", "1", addSDIprops);
            /* PropertyList addSDIprops = new PropertyList();
            addSDIprops.setProperty("sdcid","ResultEntryTables");
            addSDIprops.setProperty("u_resultentryid",resultEntryId);
            addSDIprops.setProperty("paramtype","table");
            addSDIprops.setProperty("tabletype",VariantMap.get("tabletype"));
            addSDIprops.setProperty("col1param",VariantMap.getOrDefault("col1param",null));
            addSDIprops.setProperty("col1value",VariantMap.getOrDefault("col1value",null));
            addSDIprops.setProperty("col2param",VariantMap.getOrDefault("col2param",null));
            addSDIprops.setProperty("col2value",VariantMap.getOrDefault("col2value",null));
            addSDIprops.setProperty("col3param",VariantMap.getOrDefault("col3param",null));
            addSDIprops.setProperty("col3value",VariantMap.getOrDefault("col3value",null));
            addSDIprops.setProperty("col4param",VariantMap.getOrDefault("col4param",null));
            addSDIprops.setProperty("col4value",VariantMap.getOrDefault("col4value",null));    
            addSDIprops.setProperty("col5param",VariantMap.getOrDefault("col5param",null));
            addSDIprops.setProperty("col5value",VariantMap.getOrDefault("col5value",null)); 
            addSDIprops.setProperty("col6param",VariantMap.getOrDefault("col6param",null));
            addSDIprops.setProperty("col6value",VariantMap.getOrDefault("col6value",null));    
            addSDIprops.setProperty("col7param",VariantMap.getOrDefault("col7param",null));
            addSDIprops.setProperty("col7value",VariantMap.getOrDefault("col7value",null)); */
            

            
        } catch (SapphireException e){

            response = "ERROR: "+e;
            throw new SapphireException("Error when adding table entry: "+e);
        }
        
        
        return "newkeyid1";
    }
    
    //loops through each analysis, builds lookup
    //calls lookup when adding variant to determine sequence
    public ArrayList SortVariants(){
        ArrayList varSequenceIntList = new ArrayList(); //main sorting list
        ArrayList varSequenceStrList = new ArrayList(); //for sorting X chromosomes
        ArrayList varSequenceList = new ArrayList(); 
        ArrayList sequenceLookup = new ArrayList();
        
        //pass in variant object to get the sequenceLookup master order list
       
        
        return sequenceLookup;
        
         };
    
    //for sorting numbered chromosome
    private int compInt(HashMap<String, String> a, HashMap<String, String> b){
        Integer c1;
        Integer chrA = Integer.parseInt(a.get("chr"));
        Integer chrB = Integer.parseInt(b.get("chr"));
        c1 = chrA.compareTo(chrB);
    
            if(c1 == 0){

                Integer c2;
                Integer startA = Integer.parseInt(a.get("start"));
                Integer startB = Integer.parseInt(a.get("start"));
                c2=startA.compareTo(startB);
                return c2;
            } else {
                return c1;
            }
   
 };          
    //for sorting X chromosomes
    private int compStr(HashMap<String, String> a, HashMap<String, String> b){
    
        Integer c1;
        String chrA = a.get("chr");
        String chrB = b.get("chr");
        c1 = chrA.compareTo(chrB);

        if(c1 == 0){
            Integer c2;
            Integer startA = Integer.parseInt(a.get("start"));
            Integer startB = Integer.parseInt(a.get("start"));
            c2=startA.compareTo(startB);
            return c2;
        } else {
        return c1;
            }
    }
    
    
}

