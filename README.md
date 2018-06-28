# StrategoGame

Sztuczna inteligencja i Inżynieria wiedzy laboratorium
Ćwiczenie 3. Algorytmy rozwiązywania gier

1.	Wstęp
Grą użytą do realizacji ćwiczenia jest „Stratego” zwana inaczej „Pogrzebem”. Jest to gra dla 2 osób polegająca na tym że mając planszę N x N, gracze zamalowują po sobie kratki. Gdy zamalowane kratki wypełnią rząd, kolumnę lub linię ukośną na planszy, otrzymuje się tyle punktów, ile kratek liczy zakolorowany przez nas odcinek (minimum 2 kratki)

Zaimplementowane algorytmy:

•	Min-Max
Algorytm polega na tym, że gracz min chce minimalizować stan gry, a gracz max zmaksymalizować. Obliczamy drzewo wszystkich możliwych stanów w grze do zadanej głębokości. Po przeprowadzaniu tych symulacji wybierany zostaje ruch który jest najbardziej optymalny dla tej głębokości.
•	Alfa-Beta
Algorytm Alfa-Beta jest usprawnieniem algorytmu Min-Max, polega on na tym że odcinamy/nie sprawdzamy gałęzi, jeśli wiemy że wartości w nich są na pewno gorsze niż ta którą już wybraliśmy. Pozwala to na szybsze przeszukiwanie drzewa.
•	Alfa-Beta z sortowaniem
Usprawnieniem na które wpadłem jest sortowanie węzłów według ilości punktów. Powinno umożliwić to szybsze odcinanie węzłów które na pewno są gorsze od tego który już wybraliśmy. Dla max sortujemy od najwyższej wartości, dla min odwrotnie. Dzięki temu przeszukiwanie drzewa powinno się odbywać jeszcze sprawniej. 


2.	Badania

2.1	Czas wykonania (ms)  dla głębokości = 4.
N	4	5	6	7	8
MM vs MM	31	151	492	2115	6235
AB vs AB	10	29	90	188	284
ABS vs ABS	46	78	189	453	848

Jak widać na tabeli powyżej, najlepiej sprawuje się Alfa-Beta bez sortowania. Miałem nadzieję że sortowanie przyśpieszy przeszukiwanie drzewa, natomiast tak się nie stało. Czas który jest potrzebny do sortowania jest zbyt długi. 
 Najlepiej działa pierwotna implementacja Alfa-Bety, która jest wykonuje się przy n=8 ponad 20 razy szybciej niż Min-Max. 

2.2	Min-Max vs Alpha-Beta dla N=6
Dla MM ustawiłem głębokość 4, natomiast dla AB 6 co daje porównywalne czasy wykonywania ruchu dla tych algorytmów. 

MM vs AB	77:63 
AB vs MM	63:77 

Niestety jak widać, gra która została wybrana nie pokazuje przewagi Alfa-Bety nad min-maxem. Spowodowane jest to tym, że ważniejsze od głębokości drzewa jest to kto ma kończący ruch. Natomiast osoba która zaczyna nic nie zyskuje.

Różnice dopiero są widoczne gdy zwiększymy jeszcze głębokość o 1 w AB ,ale wtedy pojedynek jest nieuczciwy bo AB dla n=7 potrzebuje, więcej czasu niż MM dla n=4.
MM vs AB	59:81 
AB vs MM	70:70 Remis


3.	Heurystyki
Ocena stanu gry odbywa się poprzez różnicę pomiędzy punktami dwóch graczy. 
Zaimplementowane dwie inne heurystyki to: 
1) Punkty przeciwnika z podwójną wagą  
2)  Zliczanie punktów ignorując punkty przeciwnika

Zostały one przetestowane dla najszybciej wykonywanego się algorytmu czyli normalnego Alfa-Beta. 
Głębokość = 6, N = 6
AB vs 1	58:82 
1 vs AB	55:85
AB vs 2	78:62
2 vs AB	62:78
1 vs 2	70:70 Remis
2 vs 1	58:82

Z tabelki wynika, że pierwsza heurystyka lepiej się sprawuje zarówno grając przeciwko wcześniejszej Alfa-Beta jak i przeciwko drugiej heurystyce. 
Mimo tego  nadal poprzednia heurystyka oceny stanu gry, która brała różnicę punktów dwóch graczy jest minimalnie lepsza, co można wnioskować np. po wygranej z drugą heurystyką czy AB zaczyna.

4.	Podsumowanie
Pokazywałem grę kilku osobą i wszyscy dochodzili do tego samego wniosku z którym się zgadzam. 
Nie rozumiem wyboru tej gry do tego zadania. Przewaga jaką daje ostatni ruch dla gracza, który jest drugi jest bardzo duża, przez to wyniki zostają spaczone. 
Co do algorytmów, alfa- beta pozwala na ogromne zaoszczędzenie czasu, co pozwala na głębsze przeszukiwanie drzewa. 
Dzięki temu komputer staje się bardziej wymagającym przeciwnikiem.  
