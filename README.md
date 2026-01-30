# Week-3-Native

MVVM (Model-View-ViewModel) erottaa nyt sovelluksen logiikan UI:sta ja tekee ohjelmasta entistä ylläpidettävämmän kun Model sisältää datan, ViewModel hallitsee tilan ja logiikan ja View sitten vain näyttää datan ja reagoi muutoksiin.

StateFlow on Kotlinin reaktiivinen tilaobjekti joka säilyttää aina yhden ja uusimman arvon heti alusta asti jota UI kuuntelee (collectAsState()) ja päivittää aina muuttuessaan.

Yhdessä MVVM ja StateFlow siis mahdollistaa reaaliaikaisen ja yksinkertaisen tilanhallinnan Jetpack Compose-sovelluksissa.
