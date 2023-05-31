import 'package:flutter/material.dart';

import '../models/models.dart';

class ElementoBusqueda extends StatelessWidget {

  final Producto producto;

  const ElementoBusqueda({Key? key, required this.producto, }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => Navigator.of(context).pushNamed('detalle', arguments: producto),
      child: ListTile(
        title: Text(producto.nombre),
        subtitle: Text(producto.descripcion),
        trailing: Text('Precio: ${producto.precio}€')
        ),
      );
  }
}
