class PageInfo {
  int totalRows;
  int page;
  int pageSize;
  bool isFirstPage;
  bool isLastPage;

  PageInfo({
    required this.totalRows,
    required this.page,
    required this.pageSize,
    required this.isFirstPage,
    required this.isLastPage,
  });

  factory PageInfo.fromJson(Map<String, dynamic> json) =>
      PageInfo(
        totalRows: json["totalRows"],
        page: json["page"],
        pageSize: json["pageSize"],
        isFirstPage: json["isFirstPage"],
        isLastPage: json["isLastPage"],
      );
}