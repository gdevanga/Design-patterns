//
//  main.cpp
//  StrategyDynamic
//
//  Created by Girish Devanga on 12/09/20.
//  Copyright © 2020 Girish Devanga. All rights reserved.
//


#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

    // Motivation: multiple policies may be there in a software.
    // Policy may be dynamically set or set at compile time
    // Initiliaze the policy

    // Here for example, we want to format a list either as HTML or plain text


enum class Type
{
    LIST,
    HTML
};

class Strategy
{
public:
    virtual ~Strategy(){};
    virtual void start(ostringstream& oss) = 0;
    virtual void append(ostringstream& oss, string& item) = 0;
    virtual void end(ostringstream& oss) = 0;
};

class HTMLStrategy : public Strategy
{
public:
    void start (ostringstream& oss)
    {
        oss << "<ul>" << endl;
    }
    void append (ostringstream& oss, string& item)
    {
        oss << "  <li>" << item << "</li>" << endl;
    }
    void end (ostringstream& oss)
    {
        oss << "</ul>" << endl;
    }
};

class ListStrategy : public Strategy
{
    int counter = 0;
public:
    void start (ostringstream& oss)
    {
        counter = 0;
    }
    void append (ostringstream& oss, string& item)
    {
        oss << counter << ": " << item << endl;
        ++counter;
    }
    void end (ostringstream& oss)
    {
    }
};

class TextProcessor
{
    unique_ptr<Strategy> strategy;
    ostringstream oss;
public:
    TextProcessor() {}
    void setStrategy(Type t)
    {
        switch (t)
        {
            case Type::LIST:
                strategy = make_unique<ListStrategy>();
                break;
            case Type::HTML:
                strategy = make_unique<HTMLStrategy>();
                break;
        }
    }
    
    void append_list(vector<string> list)
    {
        oss.str("");
        oss.clear();
        
        strategy->start(oss);
        for (auto ele : list)
        {
            strategy->append (oss,ele);
        }
        strategy->end(oss);
    }
    string getStr()
    {
        return oss.str();
    }
};

int main(int argc, const char * argv[]) {
    TextProcessor tp;
    tp.setStrategy(Type::LIST);
    tp.append_list ( {"one", "two", "three"});
    cout << tp.getStr();
    
    tp.setStrategy(Type::HTML);
    tp.append_list ( {"four", "five", "six"});
    cout << tp.getStr();
    
    return 0;
}
