# Week-4-Native

Videon linkki: https://unioulu-my.sharepoint.com/:v:/g/personal/jlievone24_students_oamk_fi/IQC8ip6u1qCHRbKyLxo8A2s-AYZLkAOZkTLtH5jiMuiwT8Q?e=ibWTRx&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

# Navigointi Jetpack Composessa

Navigointi Jetpack Composessa tarkoittaa näkymien (Composable-funktioiden) välistä siirtymistä yhden Activityn sisällä. Sovellus käyttää Single-Activity-arkkitehtuuria, jossa kaikki ruudut ovat composableja ja niiden välinen navigointi hoidetaan Navigation Compose -kirjastolla. Navigointi perustuu reitteihin (routes), joista käyttäjä voi siirtyä painikkeiden tai muiden UI-elementtien kautta ilman, että uusia Activityja luodaan.

# NavHost ja NavController

NavController vastaa navigaatiosta sovelluksessa. Sen avulla siirrytään reitiltä toiselle (esim. navigate("calendar")) ja hallitaan back stackia. NavHost määrittelee navigaation rakenteen. Se kertoo mikä on aloitusnäkymä, mitkä reitit sovelluksessa on ja mitä composablea kukin reitti vastaa. NavHost sijaitsee MainActivityssä, ja kaikki näkymät on määritelty sen sisällä.

# Sovelluksen navigaatiorakenne (Home ↔ Calendar)

Sovelluksessa on nyt kaksi pääruutua: HomeScreen jossa tehtävälista ja CalendarScreen, jossa tehtävät ryhmiteltynä päivämäärän mukaan sekä SettingsScreen josta voi käydä laittamassa tumman tilan päälle ettei silmiin satu. Navigointi on toteutettu sitten niin että HomeScreeniltä käyttäjä voi siirtyä CalendarScreenille tai SettingsScreenille yläpalkin kalenteri-ikonista ja ratasikonista (kutsuu navController.navigate(route)). CalendarScreeniltä voi palata takaisin HomeScreenille listakuvakkeesta ja SettingsScreeniltä voi mennä kummalle vain.

# Arkkitehtuuri: MVVM + Navigointi

Sovellus noudattaa tuttua ja turvallista MVVM-arkkitehtuuria, eli Model: Task-data (Task-dataluokka), ViewModel: TaskViewModel, View: HomeScreen, CalendarScreen ja dialogit

Yksi ja sama TaskViewModel luodaan MainActivityssä ja jaetaan molemmille ruuduille navigoinnin kautta. Näin ViewModelia ei aleta luomaan uudelleen navigoinnin yhteydessä ja se säilyttää tilansa ruudulta toiselle siirryttäessä.

Sekä HomeScreen että CalendarScreen lukevat tehtävälistan ViewModelista (tasks.collectAsState()) ja käyttävät samoja funktioita (addTask, updateTask, removeTask, toggleDone), joten muutokset yhdessä näkymässä näkyvät välittömästi toisessa ja sovelluksen tila pysyy sitten yhtenäisenä

# CalendarScreenin toteutus

CalendarScreen esittää tehtävät kalenterimaisessa muodossa eli tehtävät ryhmitellään dueDate-kentän perusteella ja jokaiselle päivämäärälle näytetään otsikko, ja sitten tuon otsikon alla näkyvät kyseisen päivän tehtävät listana. Toteutus perustuu groupBy { it.dueDate } ja LazyColumn-rakenteeseen, jossa päivämäärä ja sen tehtävät näytetään peräkkäin. Niin, ja tehtävää painamalla avautuu sama editointidialogi kuin HomeScreenissä.

# AlertDialog – addTask ja editTask

Uuden tehtävän lisäys ja olemassa olevan muokkaus on toteutettu AlertDialogilla, ei erillisillä navigointiruuduilla. HomeScreenin “Add Task” -painike avaa AddDialogin jossa käyttäjä syöttää tehtävän tiedot ja tallennus kutsuu viewModel.addTask(...).

Tehtävää painamalla avautuu DetailDialog jossa kentät on esitäytetty valitun tehtävän tiedoilla. Käyttäjä voi tallentaa muutokset (updateTask), poistaa tehtävän (removeTask) tai ihan vain peruuttaa ilman muutoksia jos oli väärä tehtävä.

Dialogit käyttävät callbackeja eivätkä omista ViewModelia itse.
