foreach $line (<STDIN>) {
	if ($line =~ /\|\d/) {
		chomp($line);
		$line =~ s/^.//;
		$line =~ s/\'//g;
		$line =~ s/\{\{.*?\}\}//g;
		@l = split /\|\|/, $line;
		if ($l[2] == "-") {
			printf"{'home' : '%s' , 'away' : '%s'},\n", $l[1], $l[4]; 
		} else {
			printf "{'home' : '%s' , 'away' : '%s', 'hScore' : '%s', 'aScore' : '%s', 'htry' : '%s', 'atry' : '%s'},\n", $l[1], $l[4], $l[2], $l[3],$l[0], $l[5];
		}
	}
}
