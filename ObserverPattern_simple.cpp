//
//  main.cpp
//  Observer
//
//  Created by Girish Devanga on 12/09/20.
//  Copyright © 2020 Girish Devanga. All rights reserved.
//

#include <iostream>
#include <vector>
#include <mutex>
//#include <thread>

using namespace std;



class PersonListenor
{
public:
    virtual void update (int age) = 0;
    virtual ~PersonListenor() {}
    static std::mutex mtx;
    
};

std::mutex PersonListenor::mtx;

class AgeListenor : public PersonListenor
{
public:
    void update (int age)
    {
        cout << " new age : " << age << endl;
    }
};

class VoteListenor : public PersonListenor
{
    int prevAge = 0;
public:
    void update (int age)
    {
        if (prevAge < 18 && age == 18)
        {
            prevAge = age;
            cout << " Hurray! You can vote now!!" << endl;
        }
    }
};

class Person
{
    int age;
    vector <PersonListenor*> lisners;
public:
    explicit Person (int age) : age (age) { }
    void setAge(int age) { this->age = age; notify();}
    int getAge() { return age;}
    
    void addListnr (PersonListenor* listnr)
    {
        lock_guard<mutex> lck (PersonListenor::mtx);
        for (const auto ele : lisners)
        {
            if (ele == listnr)
                return;
        }
        lisners.push_back(listnr);
    }
    
    void remListnr (PersonListenor* listnr)
    {
        lock_guard<mutex> lck (PersonListenor::mtx);
        for (int i = 0; i < lisners.size(); ++i)
        {
            if (lisners[i] == listnr)
            {
                lisners.erase(lisners.begin() + i);
            }
                
        }
    }
    
    void notify ()
    {
        for (const auto ele : lisners)
            ele->update(age);
    }
};

int main(int argc, const char * argv[]) {
   
    Person p {17};
    PersonListenor* pl = new AgeListenor ();
    PersonListenor* vl = new VoteListenor ();
    
    
    
    p.addListnr(pl);
    p.addListnr(vl);
    
    p.setAge(18);
    p.setAge(19);
    
    p.remListnr(pl);
    
    p.setAge(20);
    

    delete pl;
    delete vl;
    
    return 0;
}
