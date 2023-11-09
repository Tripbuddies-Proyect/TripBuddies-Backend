Feature: Creación de lugares turísticos

  Scenario Outline: Crear un nuevo lugar turístico
    Given el operador de turismo con email "<email>" y contraseña "<password>" está autenticado
    When el operador crea un nuevo lugar con el título "<titulo>", descripción "<descripcion>", imagen "<imagen>", precio "<precio>", destino "<destino>", fecha "<fecha>", hora "<hora>" y origen "<origen>"
    Then el nuevo lugar turístico debería ser guardado con el título "<titulo>"

    Examples:
      | email            | password | titulo          | descripcion            | imagen                 | precio | destino    | fecha       | hora  | origen    |
      | operador@tour.com | pass123  | Machu Picchu    | Maravilla del mundo    | url_to_machu_picchu    | 250.0  | Cusco      | 2023-12-10  | 08:00 | Lima      |
