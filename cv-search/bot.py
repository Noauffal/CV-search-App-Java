import sys
import os
import yaml
import random
from yaml.loader import Loader
import json

def json_loader(filepath):
    data = json.load(open(filepath))
    return data


def yaml_loader(filepath):
    """loads a yaml file"""
    with open(filepath, "r") as file_descriptor:
        data = yaml.load(file_descriptor,Loader=Loader)
    return data

def yaml_dump(filepath,data):
    """dumps data to a yaml file"""
    with open(filepath,"w") as file_descriptor:
        yaml.dump(data, file_descriptor)

if __name__ == "__main__":
    mydir = '.'
    filelist = [ f for f in os.listdir(mydir) if f.endswith(".yaml") ]
    for f in filelist:
        os.remove(os.path.join(mydir, f))
    nbApplicant = 5
    if len(sys.argv) == 2:
        nbApplicant=int(sys.argv[1])
    names = json_loader("names.json")
    skills = json_loader("skills.json")
    companies = json_loader("company.json")
    data = {}
    
    for x in range(nbApplicant):

        #Choose random name/lastName/email
        name = ""
        lastName = ""
        email = ""
        nameNumber = random.randint(1,len(names["names"]))
        counter = 0
        for i in names["names"]:
            counter +=1
            if(counter == nameNumber):
                name = i
        nameNumber = random.randint(1,len(names["names"]))
        counter = 0       
        for i in names["names"]:
            counter +=1
            if(counter == nameNumber):
                lastName = i

        #sexe
        sexe= "femme"
        sexeNumber = random.randint(1,3)
        if(sexeNumber == 1):
            sexe = "homme"
        elif(sexeNumber == 3):
            sexe = "nb"
        
        #skilllist
        skillist = {}
        nbSkills = random.randint(1,15)
        avg = 0
        for j in range(nbSkills):
            maitrise = (random.randint(1,10))*10
            skillNumber = random.randint(1,len(skills["skills"]))
            counter = 0
            for k in skills["skills"]:
                counter +=1
                if(counter == skillNumber):
                    skillist[k] = maitrise
        for skill in skillist.keys():
            avg+=skillist[skill]
        avg /= len(skillist)
        #experience
        experience = {}
        nbExperience = random.randint(1,5)
        for exp in range(nbExperience):
            companyName = ""
            companyNumber = random.randint(1,len(companies["companies"]))
            counter = 0
            for i in companies["companies"]:
                counter +=1
                if(counter == companyNumber):
                    companyName = i
            counter = 0
            expInfo = {}
            start = random.randint(1980,2021)
            end = 0
            while(end < start):
                end = random.randint(1980,2021)
            expInfo["start"]=start
            expInfo["end"]=end
            
            keywords = []
            nbkeywords = random.randint(1,15)
            for j in range(nbkeywords):
                keywordNumber = random.randint(1,len(skills["skills"]))
                counter = 0
                for k in skills["skills"]:
                    counter +=1
                    if(counter == keywordNumber):
                        if(k not in keywords):
                            keywords.append(k)
            expInfo["keywords"] = keywords
            experience[companyName]=expInfo
        

        #setData
        data["name"] = name
        data["lastName"] = lastName
        data["email"] = lastName + "@mail.com"
        data["skills"] = skillist
        data["experience"]=experience
        data["sexe"] = sexe
        data["averageAllSkills"] = avg

        filepathApp = "applicant" + str(x) + ".yaml"
        yaml_dump(filepathApp,data)
           
         


            

            


                
                




        

        
