    //
    //  main.cpp
    //  Singleton
    //
    //  Created by Girish Devanga on 20/08/20.
    //  Copyright © 2020 Girish Devanga. All rights reserved.
    //

/*
 Motivation:
 
 1) For componets (classes) it makes sense to have only one Object in the system
 eg : Data Base connections
 Object Factory
 
 2) Conctructor call is expensive
 
 3) Want to prevent anyone making copies
 
 Singleton : A component which is instantiated only once
 */

#include <iostream>
using namespace std;



class Singleton
{
    static Singleton* instance;
    
    Singleton ()
    {
        cout << "In Singleton constructor" << endl;
    }
    
    
public:
        // Not thread safe
    static Singleton* getInstance ()
    {
        if (!instance)
        {
            instance = new Singleton();
            
        }
        return instance;
    }
    
    // thread safe
    /*
     static Singleton* getInstance ()
     {
        if (!instance)
        {
            // aquire lock
            lock();
            if (!instance)
                instance = new Singleton();
        }
        return instance;
     }
     */
    
    void sayHello ()
    {
        cout << "Hello! " << endl;
    }
};
Singleton* Singleton::instance = nullptr;




// lazy instantiation + Thread safe -> c++11 style
class Singleton2
{
    Singleton2 ()
    {
        cout << "In Singleton2 constructor..." << endl;
    }
    
public:
    // delete copy constructor and copy assignment
    Singleton2 (const Singleton2&) = delete;
    void operator=(const Singleton2&) = delete;
    
    // lazy instantiation + Thread safe -> c++11 style
    static Singleton2& getInstance()
    {
        static Singleton2 obj;
        return obj;
    }
    
    void sayHello ()
    {
        cout << "Hello! " << endl;
    }
};

int main(int argc, const char * argv[]) {
    
    Singleton* s(Singleton::getInstance());
    s->sayHello();
    
    Singleton2& s2(Singleton2::getInstance());
    s2.sayHello();
    
    return 0;
}

