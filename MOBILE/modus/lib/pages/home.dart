import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:modus/models/producto.dart';
import 'package:modus/providers/producto_provider.dart';
import 'package:modus/widgets/menu.dart';
import 'package:provider/provider.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);


  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  @override
  Widget build(BuildContext context) {

    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
      drawer: Menu(),
      body: FutureBuilder(
        future: provider.listaProductos(),
          builder: (BuildContext context, AsyncSnapshot<List<ListElement>> snapshot) {
            if(snapshot.hasData){
              List<ListElement> productos = snapshot.data!;
              return ListView.separated(
                itemCount: productos.length,
                  itemBuilder: (BuildContext context, int index) {
                    ListElement producto = productos[index];

                    return GestureDetector(
                      /*
                      onTap: () => Navigator.of(context)
                        .pushNamed('details', arguments: miProvider.listaBuscados[index]),
                       */
                      child: ListTile(
                          leading: producto.imagen != null
                                ? Image.network('http://192.168.68.57' + producto.imagen!, fit: BoxFit.cover)
                                : Image.asset('assets/images/default.png', fit: BoxFit.cover),
                          title: Text(producto.nombre),
                          subtitle: Text(producto.descripcion),
                          trailing: Text('Precio: ${producto.precio}'),
                          dense: true,
                          visualDensity: VisualDensity(vertical: 4),

                      ),
                    );
                  }, separatorBuilder: (BuildContext context, int index) {
                    return Divider(
                      thickness: 1,
                    );
              },
              );
            }else{
              return Center(child: CircularProgressIndicator());
            };
          },),
    );
  }
}
