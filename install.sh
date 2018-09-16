#!/usr/bin/env bash

for d in */ ; do
    # echo "$d"
    if [[ "$d" =~ ^.*-archetype ]];then
        echo "$d"
        cd "$d"
        mvn clean install
        cd ..
    fi
done