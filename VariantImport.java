/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nch.igm.labvantage.actions;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.json.*;
import org.nch.igm.labvantage.util.aws.AWSEBCaller;
import sapphire.SapphireException;
import sapphire.action.BaseAction;
import sapphire.xml.PropertyList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.labvantage.sapphire.EncryptDecrypt;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.text.StringSubstitutor;
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
import org.nch.igm.labvantage.util.Variant;


/**
 *
 * @author spm006
 */
public class VarhouseImport extends BaseAction {
    
    String orderId;
    String varType;
    String resultEntryId;
    String resultMessage;
    String addlMsg;
    //String tableType;
    ArrayList variantWarnings;
    String rawResult;
    String sortError;
    String resultString; //incoming result json
    //JSONObject dataObj;
    //JSONArray analysesArr;
    String response;
    String log;


    
    @Override
    public void processAction(PropertyList properties) throws SapphireException {
    
        resultString = "{\"data\":{\"analyses\":[{\"alt\":\"T\",\"associatedConditions\":[{\"phenotype\":\"?Focal cortical dysplasia, type II, somatic\",\"phenotypeInheritance\":null,\"phenotypeMimNumber\":607341},{\"phenotype\":\"Lymphangioleiomyomatosis, somatic\",\"phenotypeInheritance\":null,\"phenotypeMimNumber\":606690},{\"phenotype\":\"Tuberous sclerosis-2\",\"phenotypeInheritance\":\"Autosomal dominant\",\"phenotypeMimNumber\":613254}],\"chromosome\":\"16\",\"classification\":\"acmg_likely_pathogenic\",\"classification_codes\":\"PVS1,PM2\",\"codingChange\":\"c.4602delC\",\"entrezId\":\"7249\",\"geneMimNumber\":\"191092\",\"geneSymbol\":\"TSC2\",\"notes\":[{\"CommentId\":\"d4708d761ed348a7bb08d1644a1eda0c\",\"CreatedAt\":\"2024-03-12 20:53:05.016060+00:00\",\"Text\":\"This individual is heterozygous for a frameshift variant in TSC2. TSC2 encodes the tuberin protein which forms a complex with the hamartin protein (encoded by the TSC1 gene) to restrain activity of the mTOR-associated mTORC1 complex that promotes growth (PMID: 18466115). Please note, the presence of this germline variant in combination with the somatic TSC2 variant observed in the tumor specimen likely results in biallelic alteration of this gene; however, given the proximity of these variants, phase is unable to be clearly determined.\\n\\nPathogenic loss of function variants in TSC2 are associated with tuberous sclerosis complex 2 (TSC) (OMIM: 613254), an autosomal dominant, highly penetrant, multisystem condition characterized by abnormalities of the skin, brain, kidney, heart, and lungs. Subependymal giant cell astrocytomas (SEGA) are the most commonly reported central nervous system tumor in patients with TSC (PMIDs: 8274113, 24053982, 24138953; 2022 World Health Organization (WHO) Classification of Paediatric Tumours. Surveillance and management recommendations are available for individuals diagnosed with TSC (PMIDs: 20301399, 34399110). This finding is likely contributing to the individual’s tumor type under study.\\n\\nThis variant is predicted to result in loss of function through nonsense-mediated decay of the TSC2 transcript or truncation of the encoded protein and occurs in a gene in which loss of function is a known mechanism of disease (ACMG/AMP: PVS1). This variant is absent from gnomAD v2 and v3 (ACMG/AMP: PM2). In accordance with ACMG/AMP criteria, this variant is classified as likely pathogenic.\",\"UserEmail\":\"Grant.Lammi@nationwidechildrens.org\"}],\"position\":2085261,\"probandZygosity\":\"NA\",\"proteinChange\":\"p.Asp1535fs\",\"ref\":\"TC\",\"reportTableType\":\"germline\",\"trackingAnalysisId\":\"f6120425-ba7e-4504-9a03-14081b57e369\",\"trackingCreatedTimestamp\":\"2024-02-24T13:02:40.168530\",\"transcriptId\":\"NM_000548.5\",\"vaf\":0.474025974025974},{\"alt\":\"T\",\"associatedConditions\":[{\"phenotype\":\"Cardiomyopathy, dilated, 1GG\",\"phenotypeInheritance\":\"Autosomal recessive\",\"phenotypeMimNumber\":613642},{\"phenotype\":\"Mitochondrial complex II deficiency, nuclear type 1\",\"phenotypeInheritance\":\"Autosomal recessive\",\"phenotypeMimNumber\":252011},{\"phenotype\":\"Neurodegeneration with ataxia and late-onset optic atrophy\",\"phenotypeInheritance\":\"Autosomal dominant\",\"phenotypeMimNumber\":619259},{\"phenotype\":\"Pheochromocytoma/paraganglioma syndrome 5\",\"phenotypeInheritance\":\"Autosomal dominant\",\"phenotypeMimNumber\":614165}],\"chromosome\":\"5\",\"classification\":\"acmg_likely_pathogenic\",\"classification_codes\":\"PVS1,PM2\",\"codingChange\":\"c.454G>T\",\"entrezId\":\"6389\",\"geneMimNumber\":\"600857\",\"geneSymbol\":\"SDHA\",\"notes\":[{\"CommentId\":\"caff3dea5faf485ba4f29b72f42fee50\",\"CreatedAt\":\"2024-03-12 20:53:33.590134+00:00\",\"Text\":\"This individual is heterozygous for a nonsense variant in SDHA. SDHA encodes a subunit of the mitochondrial enzyme succinate dehydrogenase (also known as complex II) which plays an important role in energy production through both the Krebs cycle and the electron transport chain (PMID: 20833333).\\n\\nPathogenic loss of function variants in SDHA are associated with autosomal dominant pheochromocytoma/paraganglioma syndrome 5 (OMIM: 614165). This cancer predisposition syndrome is characterized by increased risk of development of paragangliomas and pheochromocytomas and can also predispose to gastrointestinal stromal tumors (GIST; PMIDs: 29177515, 30068732, 22974104, 23174939, 24781757). Germline SDHA variants have also been identified in individuals with other tumors, including neuroblastoma (PMID: 30068732). Please note, the association of this variant in the development of this individual's tumor is unclear. Additional surveillance may be recommended for individuals with a germline SDHA variant (PMIDs: 28620007, 20301715).\\n\\nThis variant is predicted to result in loss of function through nonsense-mediated decay of the SDHA transcript or truncation of the encoded protein and occurs in a gene in which loss of function is a known mechanism of disease (ACMG/AMP: PVS1). This variant is very rare in gnomAD v2 and v3 (ACMG/AMP: PM2). In accordance with ACMG/AMP criteria, this variant is classified as likely pathogenic.\",\"UserEmail\":\"Grant.Lammi@nationwidechildrens.org\"}],\"position\":225560,\"probandZygosity\":\"NA\",\"proteinChange\":\"p.Glu152*\",\"ref\":\"G\",\"reportTableType\":\"germline\",\"trackingAnalysisId\":\"f6120425-ba7e-4504-9a03-14081b57e369\",\"trackingCreatedTimestamp\":\"2024-02-24T13:02:40.168530\",\"transcriptId\":\"NM_004168.4\",\"vaf\":0.476190476190476}]}}";
        
    //check incoming data for error before parsing
    boolean hasError = properties.getProperty("resultString","false").equalsIgnoreCase("Internal Pipeline Error.");
    varType = properties.getProperty("vartype","false");
    resultEntryId = properties.getProperty("resultentryid","false");
    
    
    if (hasError){
        //add sdi detail
        log += "<P>Error received from Varhouse.";
        properties.setProperty("output",response);
    }

    if (!hasError){
        try{
            JSONObject jsonObj = new JSONObject(resultString);
            JSONObject dataObj = jsonObj.getJSONObject("data");
            JSONArray analysesArr = dataObj.getJSONArray("analyses");
            
           for (int i=0;i<analysesArr.length();i++){
               
               JSONObject analysisObj = analysesArr.getJSONObject(i);
               
               //CNVs are nested one layer deeper in analysis.  CNVs return multiple analysis objects
               if(varType.equalsIgnoreCase("CNV")){
                  JSONArray analysisVariants = analysisObj.getJSONArray("variants");
                  String rawTableType = analysisObj.getString("reportTableType");
                  String tableType = null; 
                  
                  switch(rawTableType){
                      case "CNV Germline":
                          tableType = "TNGermlineCNV";
                          break;
                      case "CNV Somatic":
                          tableType = "TNSomaticCNV";
                          break;
                  }
                  
                    for(int j=0; j<analysisVariants.length(); j++){
                      
                        //data is parsed in Variant class and returns 
                        //variant hashmap to add table
                   
                     Variant Variant = new org.nch.igm.labvantage.util.Variant();  

                      log += "<P>Found copy-number variant results.";
                      JSONObject cnvAnalysisObj = analysisVariants.getJSONObject(i);
                       
                      log+="<P>Building variant. ";
                      
                     if (tableType.equals("TNGermlineCNV")){
                        //parse variant data
                      Variant.GermlineCNV newCNV = Variant.new GermlineCNV();
                      HashMap newVariant = newCNV.GermlineCNV(cnvAnalysisObj);
                        //add variant in LV
                      Variant.AddVariant(newVariant);
                      
                     } else if (tableType.equals("TNSomaticCNV")) {
                       //parse variant data
                      Variant.SomaticCNV newCNV = Variant.new SomaticCNV();
                      HashMap newVariant = newCNV.SomaticCNV(cnvAnalysisObj);
                      //add variant in LV
                      Variant.AddVariant(newVariant);
                      
                     }
                     
                  }
                      
               } else {
                       
               //if not a CNV it is a sequence variant  
               //analysisObj has the variant properties
               
                  String rawTableType = analysisObj.getString("reportTableType");
                  String tableType = null; 
                  
                  switch(rawTableType.toLowerCase()){
                      case "germline":
                          tableType = "TNGermline";
                          break;
                      case "somatic":
                          tableType = "TNSomatic";
                          break;
                  }
                  
                log += "<P>Found sequence variant results.";   
                Variant Variant = new org.nch.igm.labvantage.util.Variant();  

                log+="<P>Building variant. ";

               if (tableType.equals("TNGermline")){
                  //parse variant data
                Variant.GermlineCNV newCNV = Variant.new GermlineCNV();
                HashMap newVariant = newCNV.GermlineCNV(analysisObj);
                  //add variant in LV
                Variant.AddVariant(newVariant);

               } else if (tableType.equals("TNSomatic")) {
                 //parse variant data
                Variant.SomaticCNV newCNV = Variant.new SomaticCNV();
                HashMap newVariant = newCNV.SomaticCNV(analysisObj);
                //add variant in LV
                Variant.AddVariant(newVariant);

               }


                }
           }   
   
        } catch (JSONException e){ 
        
        response = "ERROR CAUGHT>>> "+e.toString();
        
        } //finally {  }
    
       properties.setProperty("output",response);
        
     }
    }
    
    public class OldVariant {
              
        public HashMap<String,String> SequenceVariant(JSONObject variantObj) throws JSONException{
        
            HashMap<String,String> VariantMap = new HashMap<String, String>();
            String geneSymbol;
            String transcriptId;
            String chromosome;
            String position;
            String ref;
            String alt;
            String vaf;
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
            
            reportTableType = variantObj.getString("reportTableType").toLowerCase();
            geneSymbol = variantObj.getString("geneSymbol");
            transcriptId = variantObj.getString("transcriptId");
            codingChange = variantObj.getString("codingChange");
            proteinChange = variantObj.isNull("proteinChange") ? "p.?" : variantObj.getString("proteinChange").replaceAll("\\*", "Ter");
            chromosome = variantObj.getString("chromosome");
            classification = variantObj.getString("classification");
            position = variantObj.getString("position");
            ref = variantObj.getString("ref");
            alt = variantObj.getString("alt");
            probandZygosity = variantObj.has("probandZygosity") ? StringUtils.capitalise(variantObj.getString("probandZygosity")) : "";
            vaf  = variantObj.getString("vaf");
            entrezId = variantObj.getString("entrezId");
                = variantObj.getString("");
                
            
            variantInfo = "<p><em>"+geneSymbol+"</em></p> <p>("+transcriptId+")</p><p></p><p>"+codingChange+"</p><p></p><p>"+proteinChange+"</p>";
            genomicChange = "<p>chr"+chromosome+":"+position+"</p> <p>"+ref+">"+alt+"</p>";
            
                        
            VariantMap.put("col1param", "Variant Info");
            VariantMap.put("col1value",variantInfo);
            VariantMap.put("col2param", "Genomic Change (GRCh38)");
            VariantMap.put("col4param", "Germline Variant Allele Frequency");
            VariantMap.put("col5param", "Somatic Variant Allele Frequency");
             switch(reportTableType){
                case "germline":
                    VariantMap.put("tabletype","TNGermline");
                    VariantMap.put("col3param", "Zygosity");
                    VariantMap.put("col6param", "Associated Disease/Condition");
                    VariantMap.put("col7param", "Variant Classification (ACMG/AMP Evidence)");
                case "somatic":
                    VariantMap.put("tabletype","TNSomatic");
                    VariantMap.put("col3param", "Etiology");
                    VariantMap.put("col3value", "Somatic");
                    VariantMap.put("col6param", "Associated Disease/Condition");
            }

            
            log += "<P>Building "+tableType+"variant: "+geneSymbol+".";
            
            return VariantMap;
        
        }
        
        public HashMap<String,String> CnvVariant(JSONObject variantObj) throws JSONException{
        
            HashMap<String,String> VariantMap = new HashMap<String, String>();
            
            
            
            VariantMap.put("col1param", "Variant Info");
            VariantMap.put("col1value","y");
            VariantMap.put("col2param", "Genomic Change (GRCh38)");
            VariantMap.put("col3param", "x");
            VariantMap.put("col4param", "Germline Variant Allele Frequency");
            VariantMap.put("col5param", "Somatic Variant Allele Frequency");
            VariantMap.put("col6param", "x");
            VariantMap.put("col7param", "x");
        
        return VariantMap;
        
        }
        
             
        
     public void AddTableEntry(HashMap<String,String> VariantMap) throws SapphireException {
         
         
         try {
            PropertyList addSDIprops = new PropertyList();
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
            addSDIprops.setProperty("col7value",VariantMap.getOrDefault("col7value",null));    
            
            getActionProcessor().processAction("AddSDI", "1", addSDIprops);
        
            
        } catch (SapphireException e){

            response = "ERROR: "+e;
            throw new SapphireException("Error when adding table entry: "+e);
        }
         
     }   
    }
    
}


