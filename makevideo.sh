#!/bin/sh
cd images
for f in *ppm ; do convert -quality 100 $f `basename $f ppm`jpg; done
cd ..
ffmpeg -r 24 -b 1800 -i images/%05d.jpg videos/$1.mp4
