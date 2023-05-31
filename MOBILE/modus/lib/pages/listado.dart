import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/models.dart';
import '../providers/providers.dart';
import '../widgets/widgets.dart';

class Listado extends StatefulWidget {
  const Listado({Key? key}) : super(key: key);

  @override
  State<Listado> createState() => _ListadoState();
}

class _ListadoState extends State<Listado> {

  @override
  Widget build(BuildContext context) {

    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromRGBO(68, 195, 235, 1),
        elevation: 0,
        title: Text('Listado Productos'),
      ),
      drawer: Menu(),
      body: FutureBuilder<List<Producto>>(
        future: provider.listaProductos(),
        builder: (BuildContext context, AsyncSnapshot<List<Producto>> snapshot) {
          if (snapshot.hasData) {
            List<Producto> productos = snapshot.data!;
            return ListView.builder(
              itemCount: productos.length,
              itemBuilder: (BuildContext context, int index) {
                Producto producto = productos[index];

                return Padding(
                  //padding: EdgeInsets.symmetric(vertical: 10),
                  padding: EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                  child: GestureDetector(
                    onTap: () => Navigator.of(context).pushNamed('detalle', arguments: producto),
                    child: ElementoLista(producto: producto),
                  ),
                );
              },
              itemExtent: 180,
            );
          } else if (snapshot.hasError) {
            return Center(
              child: Text("Falló la conexion"),
            ); //revisa conexion
          } else {
            return Center(child: CircularProgressIndicator());
          }
        },
      ),
    );
  }
}