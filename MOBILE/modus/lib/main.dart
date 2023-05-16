import 'package:flutter/material.dart';
import 'package:modus/pages/home.dart';
import 'package:modus/providers/producto_provider.dart';
import 'package:provider/provider.dart';

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
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes: {
        //'presentation': (context) => Presentation(),
        'home': (context) => Home(),
        //'details': (context) => Detail()
      },
      initialRoute: 'home'
    );


  }
}