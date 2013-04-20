seeingwaterfromspace
====================

NASA hackathon project to see water form space

## Download data
   wget -i modis_data_urls.txt

## Process data

Install GDAL 1.10 (RC or compile from master).

Edit config variables in transform_modis_to_arg.py (probably just year)
and then in directory with data:
    python transform_modis_to_arg.py

NOTES:

Bounding box of chile in Web Mercator:  -8420695.567404034,-7481526.898194933,-7453944.197958334,-1988447.6861175005
