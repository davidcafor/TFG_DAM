import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';

import 'pages/pages.dart';
import 'providers/providers.dart';

void main() async{
  runApp(MyState());
}

//Clase MyState para cargar los providers que usaré para obtener datos.

class MyState extends StatelessWidget {
  const MyState({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => ProductoProvider()),
        ChangeNotifierProvider(create: (_) => TiendaProvider()),
        ChangeNotifierProvider(create: (_) => InventarioProvider())
      ],
      child: MyApp(),
    );
  }
}

//Clase MyApp donde cargo las rutas que tendrá la app indicando cual es la principal.

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes: {
        //'presentation': (context) => Presentation(),
        'home': (context) => Home(),
        'listado' : (context) => Listado(),
        'ajustes' : (context) => Ajustes(),
        'detalle': (context) => Detalle(),
        'busqueda' : (context) => Busqueda()
      },
      initialRoute: 'home'
    );
  }
}