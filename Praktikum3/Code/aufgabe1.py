# -*- coding: utf-8 -*-
"""
Created on Tue Jan 27 13:17:39 2015

@author: janis
"""

### Liste an welchen Tag ein Regenschirm beobachtet wurde ###
umbrella = []

def hmmFilter():
    ### Regen an Tag 0: 50% ###
    results = [str(0.5)]
    prob0 = 0.5 # R0
    prob1 = 0   # R1
    for i in range(1, 101):
        # Berechnung Tag Rt
        prob1 = (0.7 * prob0) + (0.3 * (1 - prob0)) 
        # Regenschirm
        if umbrella[i] == 1:
            prob0 = (0.9 * prob1) / (0.9 * prob1 + 0.2 * (1 - prob1))
        # kein Regenschirm
        else:
            prob0 = (0.1 * prob1) / (0.1 * prob1 + 0.8 * (1 - prob1))
        results.append(prob0)
    return results

def printresult(results):
    for i, result in enumerate(results): 
        print 'Tag ' + str(i) + ': ' +  str(result)

### Main ###
if __name__ == '__main__':
    ### Einlesen von Regenschirm Tagen ###
    with open("file.txt", "r") as ins:
        days = [int(line.strip()) for line in ins]
        print 'Regenschirm:'
        print days
    umbrella = [1 if i in days else 0 for i in range(0,101)]
 
    printresult(hmmFilter())