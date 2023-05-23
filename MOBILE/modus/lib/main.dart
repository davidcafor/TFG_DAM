import 'package:flutter/material.dart';
import 'package:modus/pages/ajustes.dart';
import 'package:modus/pages/busqueda.dart';
import 'package:modus/pages/detalle.dart';
import 'package:modus/pages/home.dart';
import 'package:modus/pages/listado.dart';
import 'package:modus/providers/inventario_provider.dart';
import 'package:modus/providers/ip_provider.dart';
import 'package:modus/providers/producto_provider.dart';
import 'package:modus/providers/tienda_provider.dart';
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
        ChangeNotifierProvider(create: (_) => TiendaProvider()),
        ChangeNotifierProvider(create: (_) => InventarioProvider()),
        //ChangeNotifierProvider(create: (_) => IpProvider())
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
        'listado' : (context) => Listado(),
        'ajustes' : (context) => Ajustes(),
        'detalle': (context) => Detalle(),
        'busqueda' : (context) => Busqueda()
      },
      initialRoute: 'home'
    );


  }
}