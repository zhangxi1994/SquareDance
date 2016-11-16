#!/usr/bin/env python
# Remember to give credit to group 6 :)
# Written by: Chengyu Lin of group 6

import sys,os
import subprocess

data_dir = "tmp/"
repeats = 10
participants = [200, 400, 600, 800, 1000, 1500, 2000, 2500, 3000, 4000]
player = "g6"
if (len(sys.argv) > 1):
	player = sys.argv[1]

benchmark = {}
FNULL = open(os.devnull, "w")
for d in participants:
	scores = []
	for i in range(repeats):
		log_file = data_dir + str(d) + "_" + str(i) + ".log"
		with open(log_file, "wb") as log:
			subprocess.run(["java", "sqdance.sim.Simulator", "-d", str(d), "-g", player], stderr=log, stdout=FNULL) 
		with open(log_file, "rb") as log:
			log.seek(-2, 2)
			while log.read(1) != b"\n":
				log.seek(-2, 1)
			last = log.readline()
			scores += [int(s) for s in last.split() if s.isdigit()]
			log.close()
	print(str(d) + " " + str(scores))
	benchmark[d] = scores

