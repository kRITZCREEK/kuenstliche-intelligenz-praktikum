# -*- coding: utf-8 -*-
"""
Created on Tue Jan 27 13:17:39 2015

@author: janis
"""

umbrella = []

def hmmFilter():
    results = ['Tag 0: ' + str(0.5)]
    prob0 = 0.5
    prob1 = 0
    for i in range(1, 101):
        prob1 = (0.7 * prob0) + (0.3 * (1 - prob0))
        if umbrella[i] == 1:
            prob0 = (0.9 * prob1) / (0.9 * prob1 + 0.2 * (1 - prob1))
        else:
            prob0 = (0.1 * prob1) / (0.1 * prob1 + 0.8 * (1 - prob1))
        results.append(prob0)
    return results

def printresult(results):
    for i, result in enumerate(results): 
        print 'Tag ' + str(i) + ': ' +  str(result)

# Main
if __name__ == '__main__':
    with open("file.txt", "r") as ins:
        days = [int(line.strip()) for line in ins]
        print days
    
    umbrella = [1 if i in days else 0 for i in range(0,101)]
    print umbrella
 
    printresult(hmmFilter())