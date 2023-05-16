import 'package:flutter/material.dart';

class Menu extends StatelessWidget {
  const Menu({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        children: [
          ListTile(
            title: Text('Opción 1'),
            onTap: () {
              // Acción al seleccionar la opción 1
            },
          ),
          ListTile(
            title: Text('Opción 2'),
            onTap: () {
              // Acción al seleccionar la opción 2
            },
          ),
        ],
      ),
    );
  }
}
