#!/usr/bin/env bash

for d in */ ; do
    # echo "$d"
    if [[ "$d" =~ ^.*-archetype ]];then
        echo "#############  Installing Maven Archetype $d #############"
        ./mvnw -f ./"$d" clean install
    fi
done