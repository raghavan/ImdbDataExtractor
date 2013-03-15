curl ftp://ftp.fu-berlin.de/pub/misc/movies/database/  | awk '{print "ftp://ftp.fu-berlin.de/pub/misc/movies/database/"$9}' > filenames.txt

while read p; do
var2=${p##*/}
curl $p > $var2
gzip -d $var2
done < filenames.txt
