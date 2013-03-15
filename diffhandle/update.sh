curl ftp://ftp.fu-berlin.de/pub/misc/movies/database/diffs/  | awk '{print "ftp://ftp.fu-berlin.de/pub/misc/movies/database/diffs/"$9}' > newdiffs.txt

rm -r difftodownload
mkdir difftodownload

grep -vxf diffs.txt newdiffs.txt > difftodownload/difffilenames.txt

while read p; do
var2=${p##*/}
curl $p > difftodownload/$var2
tar -zxvf difftodownload/$var2
done < difftodownload/difffilenames.txt

mv diffs difftodownload/.

#run the imdbpy2sql here

rm -r difftodownload
