$(document).ready(function () {

    getListAll();

    function getListAll() {
        $.getJSON("http://localhost:8080/list", function (data) {
            console.log("İşlem Başarılı");
            var users = data;
            var sn = 1;
            $("#tbUser").empty();
            $(users).each(function () {
                $("#tbUser").append(
                    "<tr><td>" + sn + "</td><td>" + this.ad + "</td><td>" + this.soyad + "</td><td>" + this.email + "</td><td>" + this.tel +
                    "</td><td><button class='btn btn-warning' onclick='$.kayitDuzenle(" + this.id + ");return false;'>Güncelle</button></td><td><button class='btn btn-danger' onclick='$.kayitSil(" + this.id + ");return false;'>Sil</button></td></tr>");
                sn++;
            });

        });
    }

    $("#btnKaydet").click(function () {

        var ad = $("#txtAd").val();
        var soyad = $("#txtSoyad").val();
        var email = $("#txtMail").val();
        var tel = $("#txtTel").val();

        if (ad == "" || soyad == "" || email == "" || tel == "") {
            $("#sonuc").html("Lütfen Tüm Alanları Doldurunuz");
            $("#sonuc").show();
            $("#sonuc").removeClass();
            $("#sonuc").addClass("alert alert-danger");
            $("#sonuc").fadeOut(3000);
        }
        else {

            var user = {};
            user.ad = ad;
            user.soyad = soyad;
            user.email = email;
            user.tel = tel;

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/add",
                data: JSON.stringify(user),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function (data) {
                    console.log("İşlem Başarılı");
                    sonuc(1);
                    temizle();
                    getListAll();
                },
                failure: function (response) {
                    console.log(response);
                    sonuc(2);
                },
                error: function (error) {
                    console.log(error);
                    sonuc(2);
                }
            });


        }
        return false;
    });

    $.kayitDuzenle = function (id) {

        $.getJSON("http://localhost:8080/list/" + id, function (data) {
            console.log("İşlem Başarılı");
            var gelen = data;
            $(gelen).each(function () {
                $("#txtAd").val(this.ad);
                $("#txtSoyad").val(this.soyad);
                $("#txtMail").val(this.email);
                $("#txtTel").val(this.tel);
                $("#txtId").val(id);

                $("#baslik").html("Kayıt Güncelle");
                $("#btnKaydet").css("display", "none");
                $("#btnDuzenle").css("display", "inline");
                $("#btnIptal").css("display", "inline");
            });
        });
    };

    $("#btnDuzenle").click(function () {
        var ad = $("#txtAd").val();
        var soyad = $("#txtSoyad").val();
        var email = $("#txtMail").val();
        var tel = $("#txtTel").val();
        var id = $("#txtId").val();

        if (ad == "" || soyad == "" || email == "" || tel == "") {
            $("#sonuc").html("Lütfen Tüm Alanları Doldurunuz");
            $("#sonuc").show();
            $("#sonuc").removeClass();
            $("#sonuc").addClass("alert alert-danger");
            $("#sonuc").fadeOut(3000);
        }
        else {
            var user = {};
            user.ad = ad;
            user.soyad = soyad;
            user.email = email;
            user.tel = tel;

            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/update/" + id,
                data: JSON.stringify(user),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function (data) {
                    console.log("İşlem Başarılı");
                    sonuc(1);
                    temizle();
                    getListAll();

                    $("#baslik").html("Yeni Kayıt");
                    $("#btnKaydet").css("display", "inline");
                    $("#btnDuzenle").css("display", "none");
                    $("#btnIptal").css("display", "none");

                    temizle();

                    $("#sonuc").html("Kayıt Güncellendi");
                    $("#sonuc").show();
                    $("#sonuc").removeClass();
                    $("#sonuc").addClass("alert alert-success");
                    $("#sonuc").fadeOut(3000);
                },
                failure: function (response) {
                    console.log(response);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
        return false;
    });

    $.kayitSil = function (id) {

        if (!confirm("Kayıt Silinecek Onaylıyor musunuz?")) {
            return false;
        }

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/delete/" + id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            cache: false,
            success: function (data) {
                console.log("İşlem Başarılı");
                sonuc(1);
                temizle();
                getListAll();

                $("#sonuc").html("Kayıt Silindi");
                $("#sonuc").show();
                $("#sonuc").removeClass();
                $("#sonuc").addClass("alert alert-success");
                $("#sonuc").fadeOut(3000);
            },
            failure: function (response) {
                console.log(response);
            },
            error: function (error) {
                console.log(error);
            }
        });
    };

    $("#btnIptal").click(function () {
        $("#baslik").html("Yeni Kayıt");
        $("#btnKaydet").css("display", "inline");
        $("#btnDuzenle").css("display", "none");
        $("#btnIptal").css("display", "none");

        temizle();

        return false;
    });

    function sonuc(x) {
        if (x == 1) {
            $("#sonuc").show();
            $("#sonuc").html("Kayıt Eklendi");
            $("#sonuc").removeClass();
            $("#sonuc").addClass("alert alert-success");
            $("#sonuc").fadeOut(3000);
        } else {
            $("#sonuc").show();
            $("#sonuc").html("Hata oluştu");
            $("#sonuc").removeClass();
            $("#sonuc").addClass("alert alert-danger");
            $("#sonuc").fadeOut(3000);
        }

    }

    function temizle() {
        $("#txtAd").val("");
        $("#txtSoyad").val("");
        $("#txtMail").val("");
        $("#txtTel").val("");
        $("#txtId").val("");
    }
})