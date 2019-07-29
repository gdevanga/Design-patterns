// ObserverPattern.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <iostream>
#include <vector>

using namespace std;

class IDisplayObserver
{
public:
	virtual void update(int t, int p, int h) {}
};

class CWeatherData
{
	int nTemp{ 0 };
	int nPressure{ 0 };
	int nHumidity{ 0 };
	vector<IDisplayObserver*> obsV;

	void notifyObservers()
	{
		for (IDisplayObserver* ob : obsV)
			ob->update(nTemp, nPressure, nHumidity);
	}

public:
	void addObserver(IDisplayObserver* o)
	{
		obsV.push_back(o);
	}

	void removeObserver(IDisplayObserver* o)
	{
		for (auto it = obsV.cbegin(); it != obsV.cend(); ++it)
		{
			if (*it == o)
			{
				obsV.erase(it);
				break;
			}
		}
	}

	void setWeather(int t, int p, int h)
	{
		nTemp = t;
		nPressure = p;
		nHumidity = h;

		notifyObservers();
	}
};

class CDisp1 : IDisplayObserver
{
	int nTemp{ 0 };
	int nPressure{ 0 };
	int nHumidity{ 0 };
	CWeatherData* data;

	void display()
	{
		cout << "Displaying from CDisp1 obj: ";
		cout << "Temperature :" << nTemp << "\t Pressure :" << nPressure << "\t Humidty :" << nHumidity << endl;
	}

public:

	CDisp1(CWeatherData* data)
	{
		cout << "Disp1 obj created!" << endl;
		this->data = data;
		data->addObserver(this);
	}
	~CDisp1()
	{
		cout << "Disp1 obj destroyed!" << endl;
		data->removeObserver(this);
	}

	void update(int t, int p, int h)
	{
		nTemp = t;
		nPressure = p;
		nHumidity = h;

		display();
	}

};

class CDisp2 : IDisplayObserver
{
	int nTemp{ 0 };
	int nPressure{ 0 };
	int nHumidity{ 0 };
	CWeatherData* data;

	void display()
	{
		cout << "Displaying from CDisp2 obj: ";
		cout << "Temperature :" << nTemp << "\t Pressure :" << nPressure << "\t Humidty :" << nHumidity << endl;
	}

public:
	CDisp2(CWeatherData* data)
	{
		cout << "Disp2 obj created!" << endl;
		this->data = data;
		data->addObserver(this);
	}
	~CDisp2()
	{
		cout << "Disp2 obj destroyed!" << endl;
		data->removeObserver(this);
	}

	void update(int t, int p, int h)
	{
		nTemp = t;
		nPressure = p;
		nHumidity = h;

		display();
	}
};
int main()
{
	CWeatherData* data = new CWeatherData();

	CDisp1* d1 = new CDisp1(data);
	
	data->setWeather(10, 15, 19);

	CDisp2* d2 = new CDisp2(data);
	
	data->setWeather(50, 55, 59);

	delete d1;

	data->setWeather(60, 65, 69);

	getchar();

    return 0;
}

