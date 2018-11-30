#!/usr/bin/env bash

rm /home/mouhammed/IdeaProjects/View/scripts/grammar.txt
cd /home/mouhammed/IdeaProjects/View/website/docs
for d in $(find . -name "*.md")
do
  echo $d >> /home/mouhammed/IdeaProjects/View/scripts/grammar.txt
  write-good $d --no-adverb --no-passive --no-tooWordy >> /home/mouhammed/IdeaProjects/View/scripts/grammar.txt
  java -jar /home/mouhammed/opt/LanguageTool-4.3/languagetool-commandline.jar -l en-US $d >> /home/mouhammed/IdeaProjects/View/scripts/grammar.txt
done
echo Done
