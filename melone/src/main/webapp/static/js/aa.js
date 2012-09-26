var appCache = window.applicationCache;
var tryReloadingTheAppcache = false;
if ("undefined" !== typeof appCache) {
	appCache.addEventListener("updateready", function() {
		if (this.status === this.UPDATEREADY) {
			this.swapCache();
			debug("CACHE SWAPPED")
		} else {
			debug("CACHE NOT SWAPPED")
		}
	}, false);
	appCache.addEventListener("error", function() {
		debug("Appcache Update error.");
		tryReloadingTheAppcache = true
	}, false)
}
var verbose_debug = window.verbose_debug = false;
if (window.location.hash.toLowerCase() === "#debug") {
	window.verbose_debug = true
}
var debug = function(g) {
	if (!window.verbose_debug) {
		return
	}
	var h = window.console, d, b, e;
	if (typeof h === "undefined") {
		return
	}
	if (typeof h.groupCollapsed !== "undefined"
			&& typeof h.trace !== "undefined") {
		h.groupCollapsed(g);
		for (e = 1, d = arguments, b = d.length; e < b; e++) {
			h.debug(d[e])
		}
		h.trace();
		h.groupEnd()
	} else {
		h.log("<log>");
		for (e = 0, d = arguments, b = d.length; e < b; e++) {
			var f = d[e];
			h.log("object" === typeof f ? JSON.stringify(f) : f)
		}
		h.log("</log>")
	}
};
var Datastore = (function() {
	var a;
	var d = false;
	var c;
	var b = null;
	return {
		offline_supported : false,
		products : [],
		config : {},
		store : {},
		_onlineStatus : "ONLINE",
		_doingSync : false,
		_doingSyncProducts : false,
		_doingSyncCustomers : false,
		init : function(f, i) {
			c = this;
			$(this).bind("online", function(k, j) {
			});
			$(this).bind("error", function(k, j) {
			});
			$(this).bind("sync", function(k, j) {
			});
			$(this).bind("offline", function(k, j) {
				debug("Triggered OFFLINE")
			});
			debug("INIT");
			c.retailer_id = f;
			var g = false;
			var h = function() {
				c.bootstrapLocalDataStore(i)
			};
			var e = function(j) {
				if (c.config === null) {
					g = true
				} else {
					if (c.config.version === null) {
						g = true
					}
				}
				debug("INIT: local datastore");
				c.store.initLocalDataStore(h, g)
			};
			debug("INIT: checking for the init of the config table");
			c.getConfig(c.retailer_id, e)
		},
		triggerStatusOffline : function() {
			if (this._onlineStatus != "OFFLINE") {
				this._onlineStatus = "OFFLINE";
				$(this).trigger("offline")
			}
		},
		triggerStatusOnline : function() {
			this._onlineStatus = "ONLINE";
			$(this).trigger("online")
		},
		triggerStatusSync : function() {
			this._onlineStatus = "SYNC";
			$(this).trigger("sync")
		},
		checkOfflineCapabilities : function(e) {
			var g = this;
			try {
				window.indexedDB = window.mozIndexedDB
						|| window.webkitIndexedDB;
				if (Modernizr.websqldatabase) {
					g.store = Datastore_sqlite;
					debug("INIT: Open DB");
					g.store.db = window.openDatabase("Vend_POS", "1.0",
							"Vend POS", 50000000, function() {
								debug("Database is setup")
							});
					g.offline_supported = true;
					g.store.offline_storage_engine = "html5";
					if (!g.store.db) {
						alert("Failed to open the database on disk.  You may be out of disk space on this machine.  Vend will not load.")
					}
					e()
				} else {
					if (window.google && google.gears && google.gears.factory) {
						debug("INIT: Using GEARS");
						g.store = Datastore_sqlite;
						g.store.db = google.gears.factory
								.create("beta.database");
						g.store.db.open("Vend-POS");
						if (!g.store.db) {
							alert("Failed to open the database on disk.  You may be out of disk space on this machine.  Vend will not load.")
						}
						g.offline_supported = true;
						g.store.offline_storage_engine = "gears";
						e()
					} else {
						if (Modernizr.localstorage) {
							g.store.offline_storage_engine = null;
							g.offline_supported = false;
							e()
						} else {
							if (Modernizr.indexeddb) {
								g.store.offline_storage_engine = null;
								g.offline_supported = false;
								e()
							} else {
								g.store.offline_storage_engine = null;
								g.offline_supported = false;
								e()
							}
						}
					}
				}
			} catch (f) {
				debug("ERROR: on opening DB");
				g.store.offline_storage_engine = null;
				g.offline_supported = false;
				e()
			}
		},
		checkForDuplicateBrowserSessionChange : function(g) {
			var f = this;
			var e = f.config.browser_session_id;
			f.getConfig(f.config.retailer_id, function() {
				if (f.config.browser_session_id
						&& e != f.config.browser_session_id) {
					g(true)
				}
				g(false)
			})
		},
		loadDataForRegister : function(f, j) {
			var h = this, g = $("#product_search_product_id"), i = $("#product_search_product_sku"), e = $("#register-input");
			if (null === b) {
				b = QuickKeysSell;
				b.init(f.quick_keys_template, function(k) {
					if ("undefined" !== typeof k.product_id) {
						g.val(k.product_id);
						e.triggerHandler("submit")
					} else {
						if ("undefined" !== typeof k.sku) {
							i.val(k.sku);
							e.triggerHandler("submit")
						} else {
							alert("Could not add product")
						}
					}
				})
			} else {
				b.reload(f.quick_keys_template)
			}
			if ("function" === typeof j) {
				j()
			}
		},
		getConfig : function(f, e) {
			var h = this;
			var g = JSON.parse(window.localStorage.getItem("config"));
			if (g === null || typeof g != "object") {
				g = {
					retailer_id : f,
					user_hash : null,
					account_type : null,
					browser_session_id : null,
					display_retail_price_tax_inclusive : null,
					cashier_discount : null,
					last_syncd : null,
					last_sync_product : null,
					last_sync_customer : null,
					register_id : null,
					register_sale_id : null,
					outlet_id : null,
					account_state : null,
					culture : null,
					user_name : null,
					user_display_name : null,
					outlet_name : null,
					version : null
				};
				h.config = g;
				e()
			} else {
				h.config = g;
				e()
			}
		},
		saveConfig : function(f, e) {
			var g = this;
			debug("saving config");
			window.localStorage.setItem("config", JSON.stringify(f));
			g.config = f;
			if ("function" === typeof e) {
				e()
			}
		},
		getRegisters : function(f, e) {
			var j = this;
			var g = JSON.parse(window.localStorage.getItem("registers"));
			if (g === null || (typeof g != "object" && typeof g != "array")) {
				g = []
			}
			if (f) {
				var k = [];
				for ( var h = 0; h < g.length; h++) {
					if (g[h].outlet_id == f) {
						k.push(g[h])
					}
				}
				g = k
			}
			if ("function" === typeof e) {
				e(g)
			}
		},
		getRegister : function(g, e) {
			var f = this;
			f.getRegisters(null, function(h) {
				for ( var j = 0; j < h.length; j++) {
					if (h[j].id == g) {
						var k = h[j];
						e(k)
					}
				}
				if (h.length === 0) {
					e(null)
				}
			})
		},
		saveRegisters : function(f, e) {
			var g = this;
			window.localStorage.setItem("registers", JSON.stringify(f));
			g.registers = f;
			if ("function" === typeof e) {
				e()
			}
		},
		saveRegister : function(f, e) {
			var g = this;
			debug("saving register");
			g.getRegisters(null, function(k) {
				var j = [];
				var h = false;
				for ( var l = 0; l < k.length; l++) {
					var m = k[l];
					if (m.id == f.id) {
						m = f;
						h = true
					}
					j.push(m)
				}
				if (!h) {
					j.push(f)
				}
				window.localStorage.setItem("registers", JSON.stringify(j));
				if ("function" === typeof e) {
					e()
				}
			});
			if ("function" === typeof e) {
				e()
			}
		},
		getCustomer : function(j, f) {
			var i = this;
			var e = function(k) {
				if (k === null || k.status == "error") {
					i.setSyncStatusOffline();
					f()
				} else {
					if (k.customers.length == 1) {
						if ("function" === typeof f) {
							f(k.customers[0])
						}
					} else {
						f(null)
					}
				}
			};
			var h = function() {
				f(null)
			};
			var g = "/api/customers?id=" + j;
			$.ajax({
				url : g,
				dataType : "json",
				success : e,
				error : h
			})
		},
		getCustomerByCode : function(j, f) {
			var i = this;
			var e = function(k) {
				if (k === null || k.status == "error") {
					i.setSyncStatusOffline();
					f()
				} else {
					if (k.customers.length == 1) {
						if ("function" === typeof f) {
							f(k.customers[0])
						}
					} else {
						f(null)
					}
				}
			};
			var h = function() {
				f({})
			};
			var g = "/api/customers";
			$.ajax({
				url : g,
				method : "POST",
				data : {
					code : j
				},
				dataType : "json",
				success : e,
				error : h
			})
		},
		getPaymentTypes : function(e) {
			var g = this;
			var f = JSON.parse(window.localStorage.getItem("payment_types"));
			if (f === null || (typeof f != "object" && typeof f != "array")) {
				f = []
			}
			if ("function" === typeof e) {
				e(f)
			}
		},
		deletePaymentTypes : function(e) {
			window.localStorage.removeItem("payment_types");
			if ("function" === typeof e) {
				e()
			}
		},
		getPaymentType : function(h, f) {
			var g = this;
			var e = false;
			g.getPaymentTypes(function(j) {
				for ( var l = 0; l < j.length; l++) {
					if (j[l].id == h) {
						var k = j[l];
						e = true;
						f(k);
						break
					}
				}
				if (!e) {
					f(null)
				}
			})
		},
		savePaymentType : function(f, e) {
			var g = this;
			g
					.getPaymentTypes(function(k) {
						var l = [];
						var h = false;
						for ( var m = 0; m < k.length; m++) {
							var j = k[m];
							if (j.id == f.id) {
								j = f;
								h = true
							}
							l.push(j)
						}
						if (h === false) {
							l.push(f)
						}
						window.localStorage.setItem("payment_types", JSON
								.stringify(l));
						if ("function" === typeof e) {
							e()
						}
					});
			if ("function" === typeof e) {
				e()
			}
		},
		getTaxes : function(e) {
			var g = this;
			var f = JSON.parse(window.localStorage.getItem("taxs"));
			if (f === null || (typeof f != "object" && typeof f != "object")) {
				f = []
			}
			if ("function" === typeof e) {
				e(f)
			}
		},
		getTax : function(g, e) {
			var f = this;
			f.getTaxes(null, function(j) {
				for ( var h = 0; h < j.length; h++) {
					if (j[h].id == g) {
						var k = j[h];
						e(k)
					}
				}
				e(null)
			})
		},
		saveTax : function(f, e) {
			var g = this;
			g.getTaxes(function(j) {
				var m = [];
				var h = false;
				for ( var k = 0; k < j.length; k++) {
					var l = j[k];
					if (l.id == f.id) {
						l = f;
						h = true
					}
					m.push(l)
				}
				if (!h) {
					m.push(f)
				}
				window.localStorage.setItem("taxs", JSON.stringify(m));
				if ("function" === typeof e) {
					e()
				}
			});
			if ("function" === typeof e) {
				e()
			}
		},
		saveRegisterSale : function(g, e) {
			var f = this;
			debug("SAVE: save register sale " + g.id + " total = "
					+ g.total_price + " with status " + g.status
					+ " with sync_status " + g.sync_status);
			g.register_id = f.config.register_id;
			f.store.saveRegisterSale(g, e)
		},
		bootstrapLocalDataStore : function(f) {
			var h = this;
			debug("Bootstrapping");
			var k = false;
			var g = false;
			var j = function() {
				debug("REFRESH: DO refresh taxes");
				if (k) {
					debug("Offline is Bootstrapped");
					h.refreshConfig(function() {
						g = true;
						f();
						l()
					})
				} else {
					h.refreshConfig(l)
				}
			};
			var l = function() {
				debug("REFRESH: DO refresh registers");
				h.refreshTaxes();
				h.refreshRegisters(i)
			};
			var i = function() {
				debug("REFRESH: DO refresh payment types");
				if (!g) {
					h.refreshPaymentTypes(f)
				} else {
					h.setSyncStatusOnline();
					h.refreshPaymentTypes()
				}
			};
			var e = null;
			if (h.config.outlet_id && h.config.outlet_id.length > 0) {
				e = h.config.outlet_id
			}
			h.getRegisters(e, function(m) {
				if (h.config.register_id && m.length > 0) {
					k = true
				}
				h.getConfig(h.config.retailer_id, j)
			})
		},
		refreshConfig : function(h) {
			debug("REFRESH config");
			var f = this;
			var g = function(j) {
				if (j === null || j.status == "error") {
					f.setSyncStatusOffline();
					h()
				} else {
					if (tryReloadingTheAppcache) {
						debug("Trying to reload the appcache again.");
						appCache.update();
						tryReloadingTheAppcache = false
					}
					$(".status-online").hide();
					$(".status-offline").hide();
					$(".status-error").hide();
					$(".status-sync .status-text").html("Updating");
					$(".status-sync").show();
					f.outlet_changed = false;
					if ((typeof f.config.outlet_id != "undefined")
							&& (typeof f.config.register != "undefined")
							&& ((j.config.outlet_id !== "" && (j.config.outlet_id != f.config.register.outlet_id)))) {
						debug("changed");
						f.outlet_changed = true
					}
					f.config.domain_prefix = j.config.domain_prefix;
					f.config.account_state = j.config.account_state;
					f.config.retailer_name = j.config.retailer_name;
					f.config.currency_name = j.config.currency_name;
					f.config.currency_id = j.config.currency_id;
					f.config.outlet_id = j.config.outlet_id;
					f.config.culture = j.config.culture;
					f.config.user_name = j.config.user_name;
					f.config.user_hash = j.config.user_hash;
					f.config.account_type = j.config.account_type;
					f.config.user_display_name = j.config.user_display_name;
					f.config.outlet_name = j.config.outlet_name;
					f.config.default_customer_group_id = j.config.default_customer_group_id;
					f.config.default_customer_id = j.config.default_customer_id;
					f.config.cashier_discount = j.config.cashier_discount;
					f.config.callbacks = j.config.callbacks;
					f.culture_info = $.formatCurrency.regions[j.config.culture]
							|| $.formatCurrency.regions["en-US"];
					f.culture_info.symbol = j.config.currency_name;
					f.culture_info.colorize = true;
					var k = randomCode(12);
					f.config.browser_session_id = k;
					debug("INIT: Version now = " + f.config.version
							+ " new version = " + j.config.version);
					if (f.config.version !== null
							&& (f.config.version == "undefined" || parseFloat(f.config.version) < parseFloat(j.config.version))) {
						debug("INIT: VERSION CHANGE IN SCHEMA");
						var i = function(l, m) {
							f.config.version = j.config.version;
							f.saveConfig(f.config, h)
						};
						f.migrate(j.config, i)
					} else {
						f.config.version = j.config.version;
						f.saveConfig(f.config, h)
					}
				}
			};
			var e = function(i) {
				if (i.status == 401) {
					window.location = "/signin"
				}
				debug("There was a problem with the offline access (config).  Please try refreshing the page.");
				f.triggerStatusOffline();
				f.culture_info = $.formatCurrency.regions[f.config.culture]
						|| $.formatCurrency.regions["en-US"];
				f.culture_info.symbol = f.config.currency_name;
				f.culture_info.colorize = true;
				h()
			};
			$.ajax({
				url : "/api/config?version=" + f.config.version,
				dataType : "json",
				success : g,
				error : e
			})
		},
		migrate : function(h, l) {
			var f = h.migrate_sql;
			var g = [];
			var e = function(m) {
				var i = g.pop();
				debug("INIT: Performing a migration - " + i);
				try {
					c.store._executeDSSql(m, i, [], null, c.store.logDBError)
				} catch (n) {
				}
			};
			for ( var j = 0; j < f.length; j++) {
				var k = f[j];
				debug("Migration entry version is " + parseFloat(k.version)
						+ " our version = " + parseFloat(c.config.version));
				if (isNaN(parseFloat(c.config.version))
						|| parseFloat(k.version) > parseFloat(c.config.version)) {
					g.push(k.query);
					if (c.store.offline_storage_engine !== "html5_indexedDB") {
						c.store._doDSTransaction(e)
					}
					switch (k.table) {
					case "PriceBookEntry":
						c.config.last_sync_product = "1970-01-01";
						break;
					case "Register":
						c.config.last_sync = "1970-01-01";
						break;
					case "Customer":
						c.config.last_sync_customer = "1970-01-01";
						break;
					case "Product":
						c.config.last_sync_product = "1970-01-01";
						break
					}
				}
			}
			if ("function" === typeof l) {
				l()
			}
		},
		refreshTaxes : function(f) {
			var e = this;
			debug("REFRESH Taxes");
			$
					.ajax({
						url : "/api/taxes",
						dataType : "json",
						success : function(g) {
							if (g === null || g.status == "error") {
								e.setSyncStatusOffline()
							} else {
								$(".status-online").hide();
								$(".status-offline").hide();
								$(".status-error").hide();
								$(".status-sync .status-text").html("Updating");
								$(".status-sync").show();
								$(g.taxes).each(function() {
									var h = this;
									e.saveTax(h)
								})
							}
							if ("function" === typeof f) {
								f()
							}
						},
						error : function(g) {
							if (g.status == 401) {
								window.location = "/signin"
							}
							debug("There was a problem with the offline access (Loading taxes).  Please try refreshing the page.");
							e.triggerStatusOffline();
							if ("function" === typeof f) {
								f()
							}
						}
					})
		},
		refreshRegisters : function(f) {
			var e = this;
			debug("REFRESH Registers");
			$
					.ajax({
						url : "/api/registers",
						dataType : "json",
						success : function(j) {
							if (j === null || j.status == "error") {
								e.setSyncStatusOffline()
							} else {
								$(".status-online").hide();
								$(".status-offline").hide();
								$(".status-error").hide();
								$(".status-sync .status-text").html("Updating");
								$(".status-sync").show();
								for ( var g = 0; g < j.registers.length; g++) {
									var h = j.registers[g];
									if (e.config.register
											&& h.id == e.config.register.id
											&& (JSON
													.stringify(h.quick_keys_template) != JSON
													.stringify(e.config.register.quick_keys_template))) {
										e.config.register = h;
										e.loadDataForRegister(h)
									}
								}
								e.saveRegisters(j.registers, function() {
									if ("function" === typeof f) {
										f()
									}
								})
							}
						},
						error : function(g) {
							if (g.status == 401) {
								window.location = "/signin"
							}
							debug(
									"There was a problem with the offline access (Registers).  Please try refreshing the page.",
									"ERROR:");
							e.triggerStatusOffline();
							if ("function" === typeof f) {
								f()
							}
						}
					})
		},
		refreshPaymentTypes : function(f) {
			var e = this;
			debug("REFRESH Payment types");
			$
					.ajax({
						url : "/api/payment_types",
						dataType : "json",
						success : function(g) {
							if (g === null || g.status == "error") {
								e.setSyncStatusOffline()
							} else {
								e.deletePaymentTypes(function() {
									$(g.payment_types).each(function() {
										var h = this;
										e.savePaymentType(h)
									})
								})
							}
							if ("function" === typeof f) {
								f()
							}
						},
						error : function(g) {
							if (g.status == 401) {
								window.location = "/signin"
							}
							debug(
									"There was a problem with the offline access (PaymentTypes).  Please try refreshing the page.",
									"ERROR:");
							e.triggerStatusOffline();
							if ("function" === typeof f) {
								f()
							}
						}
					})
		},
		refreshRegisterSales : function(e, j, k) {
			var g = this;
			debug("REFRESH Register Sales");
			var h = "/api/register_sales";
			if (!j && typeof g.config.last_sync !== "undefined") {
				h += "/since/" + g.config.last_sync
			} else {
				if (j) {
					h += "/since/" + j
				}
			}
			if (g.register) {
				h += "/outlet_id/" + g.register.outlet_id
			}
			if (e && e.length > 0) {
				h += "?";
				for ( var f = 0; f < e.length; f++) {
					h += "status[]=" + e[f] + "&"
				}
			}
			$
					.ajax({
						url : h,
						dataType : "json",
						success : function(m) {
							$(".status-sync .status-text").html("Updating");
							if (m === null || m.status == "error") {
								g.setSyncStatusOffline()
							} else {
								g.store.deleteRegisterSalesWithStatus("SAVED");
								g.store.deleteRegisterSalesWithStatus("LAYBY");
								g.store
										.deleteRegisterSalesWithStatus("ONACCOUNT");
								var i = clone(m.register_sales);
								var l = function(p, o) {
									g.store.saveNewRegisterSale(p, n(o))
								};
								var n = function(o) {
									debug(i.length);
									if (i.length > 0) {
										var p = i.pop();
										p.sync_status = "UPLOADED";
										l(p, o)
									} else {
										if ("function" === typeof o) {
											o(m.register_sales)
										}
									}
								};
								n(k)
							}
						},
						error : function(m, i, l) {
							debug(i);
							if (m.status == 401) {
								window.location = "/signin"
							}
							debug(
									"There was a problem with the offline access (RegisterSaless).  Please try refreshing the page.",
									"ERROR:");
							g.triggerStatusOffline();
							g.store.getRegisterSales(null, [ "ONACCOUNT",
									"LAYBY", "SAVED" ], null, k)
						}
					})
		},
		findProductByTerm : function(h, f) {
			var i = this, g = [];
			var e = function(o) {
				if (0 === o.rows.length) {
					var j = function(l) {
						if (null === l || "error" === l.status) {
							i.setSyncStatusOffline();
							f()
						} else {
							if (0 < l.products.length) {
								i.store.saveProducts(l.products,
										i.config.retailer_id, function() {
											f(l.products)
										})
							}
						}
					};
					var m = function() {
						f()
					};
					$.ajax({
						url : "/api/products/active/1/q/" + h,
						dataType : "json",
						success : j,
						error : m
					});
					return
				}
				for ( var n = 0, k = o.rows.length; n < k; n++) {
					g[n] = o.rows.item(n)
				}
				if ("function" === typeof f) {
					f(g)
				}
			};
			i.store.getProductByTerm(h, e)
		},
		findCustomerByTerm : function(h, f) {
			var j = this;
			var i = [];
			var e = function(l) {
				if (l === null || l.status == "error") {
					j.setSyncStatusOffline();
					f()
				} else {
					if (l.customers.length > 0) {
						f(l.customers)
					}
				}
			};
			var g = function() {
				j.triggerStatusOffline();
				f([])
			};
			var k = "/api/customers/q/" + h;
			$.ajax({
				url : k,
				dataType : "json",
				success : e,
				error : g
			})
		},
		setSyncStatusOnline : function() {
			var e = this;
			debug("STATUS: SET SyncStatusOnline currently " + e._onlineStatus);
			if (!(e._doingSyncProducts || e._doingSyncCustomers)) {
				if (e._onlineStatus === "OFFLINE") {
					e._onlineStatus = "SYNC";
					debug("Trigger SYNC");
					$(e).trigger("sync");
					VendPOS.NativeBridge.setState("sync")
				} else {
					e._onlineStatus = "ONLINE";
					debug("trigger ONLINE");
					$(e).trigger("online");
					VendPOS.NativeBridge.setState("online")
				}
			}
		},
		setSyncStatusOffline : function() {
			var e = this;
			debug("STATUS: SET SyncStatusOffline currently " + e._onlineStatus);
			if (e._onlineStatus !== "OFFLINE") {
				$(e).trigger("offline");
				$(".status-sync .status-text").html("Offline");
				VendPOS.NativeBridge.setState("offline")
			}
			e._onlineStatus = "OFFLINE"
		},
		setSyncStatusError : function() {
			var e = this;
			debug("STATUS: SET SyncStatusError currently " + e._onlineStatus);
			$(e).trigger("error");
			VendPOS.NativeBridge.setState("error")
		},
		setSyncStatusSync : function() {
			var e = this;
			debug("STATUS: SET SyncStatusSync currently " + e._onlineStatus);
			if (e._onlineStatus == "OFFLINE") {
				$(e).trigger("sync")
			}
			VendPOS.NativeBridge.setState("sync");
			e._onlineStatus = "SYNC"
		},
		refreshLocalDataStore : function(g) {
			var f = this;
			f._doingSync = false;
			if (f._doingSyncProducts || f._doingSyncCustomers) {
				f._doingSync = true
			}
			if (f._doingSync === false) {
				var e = function() {
					debug("REFRESH: END of the refresh products chain");
					if (f._onlineStatus != "OFFLINE") {
						f.setSyncStatusOnline();
						debug("SAVE: Set sync time to now");
						f.setLastSyncToNow()
					} else {
						$(f).trigger("offline")
					}
					if ("function" === typeof g) {
						g()
					}
				};
				f.refreshProducts(e)
			}
		},
		refreshProducts : function(h) {
			var k = this;
			var g = true;
			var j = function() {
				k._doingSyncProducts = false;
				debug("Finished refreshing products");
				k.setSyncStatusOnline();
				var m = new Date();
				var n = m.getUTCISODate();
				k.config.last_sync_product = n;
				k.saveConfig(k.config);
				h()
			};
			var i = k.config.last_sync_product;
			var e = function(n) {
				if (n === null || n.status == "error") {
					k.setSyncStatusOffline();
					j()
				} else {
					k.setSyncStatusOnline();
					if (n.products.length > 0) {
						var m = n.products[n.products.length - 1];
						k.setSyncStatusSync();
						$(".status-online").hide();
						$(".status-offline").hide();
						$(".status-error").hide();
						$(".status-sync .status-text").html("Updating");
						$(".status-sync").show();
						var o = function() {
							var p = function() {
								if (typeof n.pagination !== "undefined") {
									debug("REFRESH: Product data is paginated");
									k.setProductUpdateStatusInfo(n.pagination);
									k.refreshProductsPaginated(
											n.pagination.page + 1, i, true, j)
								} else {
									k.setSyncStatusOnline();
									j()
								}
							};
							k.config.last_sync_product = m.updated_at;
							k.saveConfig(k.config, p)
						};
						k.store.saveProducts(n.products, k.config.retailer_id,
								o)
					} else {
						debug("REFRESH: No product updates");
						j()
					}
				}
			};
			var f = function(o, m, n) {
				if (o.status == 401) {
					window.location = "/signin"
				}
				debug("ERROR: There was a problem with the offline access (products).  Please try refreshing the page. ");
				k._doingSyncProducts = false;
				k.triggerStatusOffline();
				h()
			};
			if (k._doingSyncProducts !== true) {
				k._doingSyncProducts = true;
				$(".status-online").hide();
				$(".status-offline").hide();
				$(".status-error").hide();
				$(".status-sync").show();
				$(".status-sync .status-text").html("Updating");
				var l = "/api/products/without_inventory/1";
				if (g) {
					l += "/active/1"
				}
				if (typeof k.config.last_sync_product !== "undefined"
						&& k.config.last_sync_product !== "undefined"
						&& k.config.last_sync_product !== null) {
					l += "/since/" + k.config.last_sync_product
				}
				$.ajax({
					url : l,
					dataType : "json",
					success : e,
					error : f
				})
			} else {
				k._doingSyncProducts = false;
				k._onlineStatus = "SYNC";
				$(".status-online").hide();
				$(".status-offline").hide();
				$(".status-error").hide();
				$(".status-sync").show();
				h()
			}
		},
		refreshCustomers : function(g) {
			var k = this;
			var h = k.config.last_sync_customer;
			k._doingSyncCustomers = true;
			var j = function() {
				k._doingSyncCustomers = false;
				debug("Finished refreshing customers");
				k.setSyncStatusOnline();
				g()
			};
			var e = function(m) {
				if (m === null || m.status == "error") {
					k.setSyncStatusOffline()
				} else {
					if (m.customers.length > 0) {
						var l = m.customers[m.customers.length - 1];
						$(".status-online").hide();
						$(".status-offline").hide();
						$(".status-error").hide();
						$(".status-sync .status-text").html("Updating");
						$(".status-sync").show();
						var n = function() {
							var o = function() {
								if (typeof m.pagination !== "undefined") {
									debug("REFRESH: Customer data is paginated");
									k.setCustomerUpdateStatusInfo(m.pagination);
									k.refreshCustomersPaginated(
											m.pagination.page + 1, h, j)
								} else {
									k.setSyncStatusOnline();
									k._doingSyncCustomers = false;
									j()
								}
							};
							k.config.last_sync_customer = l.updated_at;
							k.saveConfig(k.config, o)
						};
						k.store.saveCustomers(m.customers,
								k.config.retailer_id, n)
					} else {
						debug("REFRESH: No customer updates");
						j()
					}
				}
			};
			var f = function(m, l) {
				if (m.status == 401) {
					window.location = "/signin"
				}
				debug("ERROR: There was a problem with the offline access.  Please try refreshing the page.");
				k.triggerStatusOffline();
				k._doingSyncCustomers = false;
				g()
			};
			var i = "/api/customers";
			if (typeof k.config.last_sync_customer !== "undefined"
					&& k.config.last_sync_customer !== "undefined"
					&& k.config.last_sync_customer !== null) {
				i += "/since/" + k.config.last_sync_customer
			}
			$.ajax({
				url : i,
				dataType : "json",
				success : e,
				error : f
			})
		},
		refreshCustomersPaginated : function(j, k, h) {
			var i = this;
			var e = function(l) {
				$(".status-online").hide();
				$(".status-offline").hide();
				$(".status-error").hide();
				$(".status-sync").show();
				var m = function() {
					var o = l.customers.pop();
					var n = function() {
						if (l.pagination && j + 1 <= l.pagination.pages) {
							i.setCustomerUpdateStatusInfo(l.pagination);
							i.refreshCustomersPaginated(j + 1, k, h)
						} else {
							i.setSyncStatusOnline();
							h()
						}
					};
					i.config.last_sync_customer = o.updated_at;
					i.saveConfig(i.config, n)
				};
				i.store.saveCustomers(l.customers, i.config.retailer_id, m)
			};
			var g = function(m, l) {
				if (m.status == 401) {
					window.location = "/signin"
				}
				debug("ERROR: There was a problem with the offline access.  Please try refreshing the page.");
				i.triggerStatusOffline()
			};
			var f = "/api/customers";
			if (typeof i.config.last_sync_customer !== "undefined"
					&& i.config.last_sync_customer != "undefined") {
				f += "/since/" + k
			}
			f += "/page/" + j;
			$.ajax({
				url : f,
				dataType : "json",
				success : e,
				error : g
			})
		},
		setLastSyncToNow : function() {
			var h = this;
			var j = new Date();
			var e = j.getDate();
			var i = j.getMonth();
			i++;
			var f = j.getFullYear();
			var g = f + "-" + zeroPad(i) + "-" + zeroPad(e) + " "
					+ zeroPad(j.getHours()) + ":" + zeroPad(j.getMinutes())
					+ ":" + zeroPad(j.getSeconds());
			debug("SAVE: SET last sync UTC to now " + g);
			h.config.last_sync = g;
			h.saveConfig(h.config)
		},
		resetLastSync : function(f) {
			var e = this;
			e.config.last_sync = "1970-01-01";
			e.config.last_sync_product = "1970-01-01";
			e.config.last_sync_customer = "1970-01-01";
			e.saveConfig(e.config, f)
		},
		resetLastSyncCustomers : function(f) {
			var e = this;
			e.config.last_sync_customer = "1970-01-01";
			e.saveConfig(e.config, f)
		},
		resetLastSyncProducts : function(f) {
			var e = this;
			e.config.last_sync_product = "1970-01-01";
			e.saveConfig(e.config, f)
		},
		setProductUpdateStatusInfo : function(e) {
			c.setSyncStatusOnline();
			$(".status-sync .status-text").html(
					"~" + e.page * e.page_size + " of " + e.results
							+ " products")
		},
		setCustomerUpdateStatusInfo : function(e) {
			c.setSyncStatusOnline();
			$(".status-sync .status-text").html(
					"~" + e.page * e.page_size + " of " + e.results
							+ " customers")
		},
		refreshProductsPaginated : function(j, l, h, e) {
			var i = this;
			var f = i.config;
			debug("REFRESH: Date since now " + l);
			var m = function(n) {
				i._onlineStatus = "SYNC";
				i._doingSyncProducts = true;
				var o = function() {
					var q = n.products.pop();
					var p = function() {
						if (n.pagination && j + 1 <= n.pagination.pages) {
							i.setProductUpdateStatusInfo(n.pagination);
							i.refreshProductsPaginated(j + 1, l, h, e)
						} else {
							i._doingSyncProducts = false;
							i.setSyncStatusOnline();
							e()
						}
					};
					f.last_sync_product = q.updated_at;
					i.saveConfig(f, p)
				};
				i.store.saveProducts(n.products, i.config.retailer_id, o)
			};
			var g = function(o, n) {
				if (o.status == 401) {
					window.location = "/signin"
				}
				debug("ERROR: There was a problem with the offline access.  Please try refreshing the page.");
				i._doingSyncProducts = false;
				i.triggerStatusOffline()
			};
			var k = "/api/products/without_inventory/1";
			if (h === true) {
				k += "/active/1"
			}
			if (typeof l !== "undefined" && l !== "undefined") {
				k += "/since/" + l
			}
			k += "/page/" + j;
			$.ajax({
				url : k,
				dataType : "json",
				success : m,
				error : g
			})
		},
		uploadRegisterSales : function(i) {
			var g = this;
			var e = [];
			var h = function(j) {
				debug(e.length + " Sales to upload");
				if (e.length > 0) {
					var l = e.pop();
					var k = function() {
						l = undefined;
						h(j)
					};
					g.uploadRegisterSale(l, k)
				} else {
					if ("function" === typeof j) {
						j()
					}
				}
			};
			var f = function(j) {
				e = clone(j);
				h(i)
			};
			g.store.getRegisterSales(null, null, "UPLOAD", f)
		},
		performTask : function(f, e, i, g) {
			var j = 0;
			function h() {
				var k = Math.min(j + e, f.length);
				for ( var l = j; l < k; l++) {
					i(f[l])
				}
				j += e;
				if (j < f.length) {
					setTimeout(h, 100)
				} else {
					if (typeof g === "function") {
						g()
					}
				}
			}
			h()
		},
		uploadRegisterSale : function(i, f) {
			var h = this;
			debug("UPLOAD: Found register sales to upload");
			var e = function(j) {
				if (j === null || j.status == "error") {
					h.setSyncStatusOffline();
					return
				}
				i.sync_status = "UPLOADED";
				h.saveRegisterSale(i);
				h.setSyncStatusOnline();
				if ("function" === typeof f) {
					f()
				}
			};
			var g = function(j, l, k) {
				switch (j.status) {
				case 401:
					window.location = "/signin";
					break;
				case 409:
					i.sync_status = "UPLOADED";
					break;
				case 418:
					i.sync_status = "SUSPECT";
					break;
				case 501:
				case 502:
				case 503:
				case 504:
					i.sync_status = "UPLOAD";
					break;
				case 0:
					switch (l) {
					case "timeout":
					case "error":
						h.setSyncStatusOffline();
						break
					}
					break;
				default:
					i.sync_status = "ERROR";
					h.setSyncStatusError();
					break
				}
				h.saveRegisterSale(i);
				if ("function" === typeof f) {
					f()
				}
			};
			$.ajax({
				url : "/api/register_sales",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(i),
				dataType : "json",
				success : e,
				error : g
			})
		},
		getNewRegisterSale : function(m) {
			var i = this;
			debug("CREATE: Create a new register sale and save in DB");
			var g = new Date();
			var j = g.getUTCISODate();
			var f = {
				id : guid(),
				register_id : i.config.register_id,
				register_sale_products : [],
				register_sale_payments : [],
				customer_id : null,
				user_name : null,
				sale_date : null,
				tax_rate : 0,
				tax_name : "",
				total_price : 0,
				total_tax : 0,
				payment_status : "PENDING",
				status : "OPEN",
				receipt_address : "",
				receipt_fields : {},
				short_code : randomCode(5),
				note : null,
				sync_status : "PAUSE",
				totals : {}
			};
			var e = $("#btn-save-sale");
			if (e.length && e.parent().hasClass("button-inactive")) {
				e.parent().removeClass("button-inactive")
			}
			i.register.invoice_sequence++;
			var l = parseInt(i.register.invoice_sequence, 10);
			var k = i.register.invoice_prefix;
			var h = i.register.invoice_suffix;
			f.invoice_number = String(k) + l + String(h);
			f.invoice_sequence = parseInt(l, 10);
			i.store.saveNewRegisterSale(f, function() {
				i.config.register_sale_id = f.id;
				i.saveRegister(i.register);
				i.saveConfig(i.config, function() {
					m(f)
				})
			})
		},
		getRegisterSaleOrNew : function(f) {
			var e = this;
			debug("LOOKUP: looking up register sale from id "
					+ e.config.register_sale_id);
			if (e.config.register_sale_id === undefined
					|| e.config.register_sale_id === null) {
				e.getNewRegisterSale(f)
			} else {
				debug("LOOKUP: existing sale from id "
						+ e.config.register_sale_id);
				e.store
						.getRegisterSale(
								e.config.register_sale_id,
								function(j) {
									if (j === null) {
										debug("No sale found");
										e.getNewRegisterSale(f)
									} else {
										debug("found sale");
										var i = $("#btn-save-sale"), k = $("#btn-close"), g = $("#btn-void"), h = $(
												".customer-editable",
												"#customer_details");
										if (i.length) {
											i = i.parent();
											if (j.status === "OPEN"
													|| j.status === "SAVED") {
												h.show();
												if (i
														.hasClass("button-inactive")) {
													i
															.removeClass("button-inactive")
												}
												g.show();
												k.hide()
											} else {
												h.hide();
												if (!i
														.hasClass("button-inactive")) {
													i
															.addClass("button-inactive")
												}
												g.hide();
												k.show()
											}
										}
										f(j)
									}
								})
			}
		},
		getRegisterSaleFromServer : function(e, g) {
			var f = this;
			debug("LOOKUP: lookup register_sale from server");
			$
					.ajax({
						url : "/api/register_sales/" + e,
						dataType : "json",
						success : function(k) {
							if (k === null || k.status == "error") {
								f.setSyncStatusOffline();
								g(null)
							} else {
								var l = null;
								if (k.register_sales
										&& k.register_sales instanceof Array) {
									l = k.register_sales[0]
								} else {
									if (k.register_sale) {
										l = k.register_sale
									}
								}
								var j = $("#btn-save-sale"), m = $("#btn-close"), h = $("#btn-void"), i = $(
										".customer-editable",
										"#customer_details");
								if (j.length) {
									j = j.parent();
									if (l.status === "OPEN"
											|| l.status === "SAVED") {
										i.show();
										if (j.hasClass("button-inactive")) {
											j.removeClass("button-inactive")
										}
										h.show();
										m.hide()
									} else {
										i.hide();
										if (!j.hasClass("button-inactive")) {
											j.addClass("button-inactive")
										}
										h.hide();
										m.show()
									}
								}
								g(l)
							}
						},
						error : function(i, h, j) {
							debug(h);
							g(null)
						}
					})
		},
		closeRegisterSale : function(g, h) {
			var f = this;
			var e = new Date();
			if (((g.status != "LAYBY" && g.status != "VOIDED" && g.status != "ONACCOUNT"))
					|| g.sale_date == "undefined") {
				g.sale_date = e.getUTCISODate()
			}
			if (g.status == "LAYBY") {
				g.status = "LAYBY_CLOSED"
			} else {
				if (g.status == "ONACCOUNT") {
					g.status = "ONACCOUNT_CLOSED"
				} else {
					g.status = "CLOSED"
				}
			}
			g.register_id = f.config.register_id;
			debug("SAVE: Close sale");
			f.saveRegisterSale(g, h)
		},
		updateRegisterSaleTotals : function(h, i) {
			var f = clone(h);
			var e = this;
			var g = function(m) {
				f.totals = {
					total_price : 0,
					total_tax : 0,
					total_to_pay : 0
				};
				f.register_sale_payments = [];
				f.product_groups = [];
				f.register_sale_products = [];
				var p = function(s) {
					if (s.product_id === o.product_id) {
						s.quantity += o.quantity * 1;
						s.items.push(o);
						var r = true
					}
				};
				for ( var l = 0; l < m.length; l++) {
					var o = m[l];
					var k = false;
					f.register_sale_products.push(clone(o));
					$(f.product_groups).each(p);
					if (!k) {
						f.product_groups.push({
							product_id : o.product_id,
							quantity : o.quantity,
							items : [ o ]
						})
					}
					f.totals.total_price += (o.price * 1 * o.quantity * 1);
					if (o.tax_rate > 0) {
						f.tax_rate = o.tax_rate;
						f.tax_name = o.tax_name
					}
					if (o.display_retail_price_tax_inclusive) {
						f.totals.display_retail_price_tax_inclusive = true
					}
					f.totals.total_tax += (o.tax * 1 * o.quantity * 1)
				}
				var n = clone(f.product_groups);
				var q = function(r) {
					if (n.length > 0) {
						var s = n.pop();
						q(r)
					} else {
						r()
					}
				};
				var j = function() {
					f.totals.total_price = (Math
							.round(f.totals.total_price * 100) / 100)
							.toFixed(2);
					f.totals.total_tax = (Math.round(f.totals.total_tax * 100) / 100)
							.toFixed(2);
					f.totals.total_to_pay = parseFloat(f.totals.total_price)
							+ parseFloat(f.totals.total_tax);
					var r = function(v) {
						var s = Number(f.totals.total_to_pay), y = JSON
								.parse(window.localStorage
										.getItem("payment_types")), x = {}, u, t, w;
						for (u = 0, t = y.length; u < t; u++) {
							w = y[u];
							x[w.id] = w
						}
						for (u = 0, t = v.length; u < t; u++) {
							w = v[u];
							s = s - w.amount;
							f.register_sale_payments
									.push({
										id : w.id,
										retailer_payment_type_id : w.retailer_payment_type_id,
										payment_type_id : w.payment_type_id,
										name : ("undefined" !== typeof x[w.retailer_payment_type_id]) ? x[w.retailer_payment_type_id].name
												: "Cash",
										amount : (Math.round(w.amount * 100) / 100)
												.toFixed(2)
									})
						}
						f.totals.total_to_pay = (Math.round(s * 100) / 100)
								.toFixed(2);
						f.total_price = Number(f.totals.total_price);
						f.total_tax = Number(f.totals.total_tax);
						e.saveRegisterSale(f, function() {
							e.register_sale = f;
							if ("function" === typeof i) {
								i(f)
							}
						})
					};
					e.store.getRegisterSalePayments(f, r)
				};
				q(j)
			};
			e.store.getRegisterSaleProducts(f, g)
		},
		applyDiscountToSale : function(h, k, j) {
			var g = this;
			var e = clone(h.register_sale_products);
			var i = function(m, l) {
				var n = m.pop();
				var o = function() {
					g.updateRegisterSaleTotals(h, function() {
						var p = (n.price + n.tax) - (n.price + n.tax) * k;
						n.price = n.price * k;
						n.price_set = 1;
						n.tax = n.tax * k;
						n.discount = p;
						g.store.saveRegisterSaleProduct(n, function() {
							if (m && m.length > 0) {
								i(m, l)
							} else {
								l()
							}
						})
					})
				};
				g.store.deleteRegisterSaleProduct(h, n.id, o)
			};
			var f = function() {
				g.updateRegisterSaleTotals(h, function(l) {
					j(l)
				})
			};
			if (e && e.length > 0) {
				i(e, f)
			} else {
				j(h)
			}
		},
		addDiscountToSale : function(i, l, k) {
			var h = this;
			var e = clone(i.register_sale_products);
			var g = 0;
			var j = function(n, m) {
				var o = n.pop();
				var p = (o.price + o.tax) - (o.price + o.tax) * l;
				g += p;
				if (n && n.length > 0) {
					j(n, m)
				} else {
					m()
				}
			};
			var f = function() {
				var m = {};
				m.id = guid();
				m.register_sale_id = i.id;
				m.register_id = h.config.register_id;
				m.quantity = 1;
				m.price = g;
				m.price_set = 1;
				m.display_retail_price_tax_inclusive = h.config.display_retail_price_tax_inclusive;
				m.tax = null;
				m.tax_id = null;
				m.tax_rate = null;
				m.tax_name = null;
				m.status = "ACTIVE";
				m.isNew = true;
				m.product_id = null;
				m.name = "Discount";
				m.description = "Discount";
				h.store.saveRegisterSaleProduct(m, function() {
					h.updateRegisterSaleTotals(i, function(n) {
						k(n)
					})
				})
			};
			if (e && e.length > 0) {
				j(e, f)
			} else {
				k(i)
			}
		},
		setRegister : function(e, g) {
			var f = this;
			debug("**** SET register " + e.id);
			f.config.register_id = e.id;
			f.register = e;
			g(e)
		},
		setRegisterSaleCustomer : function(i, h, f, e, j) {
			var g = this;
			g.register_sale.customer = h;
			g.register_sale.customer_id = h.id;
			g.store.setRegisterSaleCustomer(i, h, f, e, function(k) {
				g.updateRegisterSaleTotals(k, function(l) {
					j(l)
				})
			})
		},
		getRegisterSaleProductFromResultsOrNew : function(e, i, h) {
			var f = this;
			var g = {};
			g.id = guid();
			g.register_sale_id = i.id;
			g.quantity = 0;
			g.price = roundNumber(h.price, 2);
			g.price_set = 0;
			g.display_retail_price_tax_inclusive = h.display_retail_price_tax_inclusive;
			g.tax = roundNumber(h.tax, 2);
			g.tax_id = h.tax_id;
			g.tax_rate = h.tax_rate;
			g.tax_name = h.tax_name;
			g.status = "ACTIVE";
			g.isNew = true;
			g.product_id = h.id;
			g.name = h.name;
			g.image = h.image;
			return g
		},
		getRegisterSaleCustomer : function(g) {
			var f;
			var e = this;
			debug("register sale customer_id is " + g.customer_id);
			if (g.customer !== null && typeof g.customer !== "undefined"
					&& typeof g.customer_id !== "undefined") {
				debug("looking up pricing for customer " + g.customer.name);
				f = g.customer
			} else {
				debug("default customer group is "
						+ e.config.default_customer_group_id);
				f = {
					customer_group_id : e.config.default_customer_group_id
				}
			}
			return f
		},
		saveSaleItem : function(o, r, h, k, s) {
			var l = this;
			var g = l.config;
			var e = 0;
			for ( var j = 0, q = o.register_sale_products.length; j < q; j++) {
				if (o.register_sale_products[j].sequence >= e) {
					e = o.register_sale_products[j].sequence + 1;
					debug(e)
				}
			}
			var p = {};
			p.id = guid();
			p.register_sale_id = o.id;
			p.register_id = l.config.register_id;
			p.quantity = 0;
			p.price = roundNumber(r.price, 2);
			p.price_set = 0;
			p.tax = roundNumber(r.tax, 2);
			p.tax_name = r.tax_name;
			p.tax_id = r.tax_id;
			p.tax_rate = r.tax_rate;
			p.tax_name = r.tax_name;
			p.display_retail_price_tax_inclusive = r.display_retail_price_tax_inclusive;
			p.status = "VALID";
			p.sequence = e;
			p.isNew = true;
			p.product_id = r.id;
			p.name = r.name;
			p.image = r.image;
			var n = function(i) {
				if (i) {
					if (!k
							|| (p.quantity - parseFloat(h) < i.min_units && p.quantity >= i.min_units)) {
						p.price = parseFloat(roundNumber(i.price, 2));
						p.tax = parseFloat(roundNumber(i.tax, 2));
						p.tax_id = i.tax_id
					}
					l.store.saveRegisterSaleProduct(p, s)
				}
			};
			var f = p.quantity * 1 + parseFloat(h) * 1;
			if (f === 0 && f.quantity < 0) {
				f = 1
			} else {
				if (f === 0 && f.quantity > 0) {
					f = -1
				}
			}
			p.quantity = f;
			debug("EXISTING: updated quant to " + f);
			if (k) {
				debug("Price set by user");
				p.price = k;
				p.price_set = 1;
				if (p.display_retail_price_tax_inclusive) {
					p.tax = p.price - (p.price / (1 + parseFloat(p.tax_rate)));
					p.price = p.price - p.tax
				} else {
					p.tax = p.price * p.tax_rate
				}
				l.store.saveRegisterSaleProduct(p, s)
			} else {
				debug("Lookup the price from the price book");
				var m = l.getRegisterSaleCustomer(o);
				l.store.getPriceBookEntryForRegisterSale(p, m,
						l.config.default_customer_group_id,
						l.register.outlet_id, n)
			}
		},
		refreshRegisterSalePricing : function(g, e) {
			var f = this;
			f.store.getRegisterSaleProducts(g, function(h) {
				$(h).each(function() {
					var j = this;
					var k = j.quantity;
					var i = function(l) {
						f.saveSaleItem(g, l, 0, null, e)
					};
					f.store.getProduct(j.product_id, i)
				})
			})
		},
		saveRegisterSaleProductFromForm : function(g, k, j) {
			var f = this, i = g.id, h = g.sku;
			var e = function(m, l) {
				if ("undefined" !== typeof m && null !== m) {
					f.saveSaleItem(k, m, g.quantity, g.price, l)
				} else {
					l(null)
				}
			};
			if ("undefined" !== typeof i && null !== i && "" !== i) {
				f.store.getProductById(g.id, f.config.retailer_id, e, j)
			} else {
				if ("undefined" !== typeof h && null !== h && "" !== h) {
					f.store.getProductBySku(g.sku, f.config.retailer_id, e, j)
				} else {
					j(null)
				}
			}
		}
	}
})();
var Register = (function() {
	var e;
	var j;
	var b;
	var i;
	var k;
	var o;
	var g = function(q, p) {
		c(q)
	};
	var d = function(q) {
		var p = $('#table_sale_items tbody tr[data-id="' + q + '"]').eq(0);
		if (p.length) {
			p.remove()
		}
	};
	var n = function(q, p) {
		if (q.status === 510) {
			var r = JSON.parse(q.responseText);
			c({
				name : r.error,
				image : "",
				description : r.details
			})
		}
	};
	var l = function(q, p) {
		if (q.status === 510) {
			var r = JSON.parse(q.responseText);
			c({
				name : r.error,
				image : "",
				description : r.details
			})
		}
	};
	var h = function(q, p) {
		if (q.status === 510) {
			var r = JSON.parse(q.responseText);
			a(null)
		}
	};
	var c = function(p) {
		$("#product-name").html(
				'<a href="#product/' + p.product_id
						+ '?summary=1" class="tip">' + p.name + "</a>");
		$("#product-image").attr("src", p.image);
		$("div#scanned-product").stop();
		$("div#scanned-product").css("background-color", "#ffffff")
	};
	var a = function(q) {
		if (q !== null) {
			$("#remove-customer").remove();
			var p = $('<a href="#delete-customer" id="remove-customer" class="customer-editable delete tip" title="remove this customer from the sale"><img class="button-icon" src="/images/button-icon-remove.png"></a>');
			p
					.bind(
							"click",
							function() {
								$(this).hide();
								var r = {
									id : i._datastore.config.default_customer_id,
									customer_group_id : i._datastore.config.default_customer_group_id,
									name : "",
									company_name : "",
									customer_code : "WALKIN",
									balance : 0
								};
								i._datastore.register_sale.customer = null;
								i._datastore
										.setRegisterSaleCustomer(
												i._datastore.register_sale,
												r,
												i._datastore.config.default_customer_group_id,
												i._datastore.register.outlet_id,
												i.refreshCustomerPricingDisplay);
								a(r)
							});
			if (q.customer_code == "WALKIN"
					|| typeof q.customer_code == "undefined") {
				$("#customer_details span.full-name").html("No customer");
				$("#customer_details span.company").html("");
				$("#customer_details #remove-customer").remove()
			} else {
				$("#customer_details span.full-name").html(
						'<a href="#customer/' + q.id + '" class="tip">'
								+ q.name + "</a>");
				$("#customer_details span.company").html(
						'<a href="#customer/' + q.id + '" class="tip">'
								+ q.company_name + "</a>");
				if (i._datastore.register_sale.status != "LAYBY"
						&& i._datastore.register_sale.status != "ONACCOUNT") {
					$("#customer_details span.full-name").prepend(p)
				}
			}
			if (q.balance !== 0 && q.customer_code != "WALKIN") {
				if (!q.balance) {
					q.balance = "-"
				}
				$("#customer_details div.balance").html(q.balance)
						.formatCurrency(i._datastore.culture_info)
			} else {
				$("#customer_details div.balance").html("")
			}
		} else {
			$("#customer_details span.full-name").html("No customer");
			$("#customer_details span.company").html("");
			$("#customer_details div.balance").html("");
			$("#customer_details #remove-customer").remove()
		}
		$("#customer_search_customer_code").val("")
	};
	var m = function() {
		if (!Is.iOS) {
			$("#product_search_product_sku").blur().focus().select()
		}
	};
	var f = function() {
		$("#checkout_amount").focus().select()
	};
	return {
		_datastore : {},
		_registerId : null,
		_userId : null,
		_outletId : null,
		_lastSync : null,
		_interval_id : null,
		_printQueue : [],
		gui : {},
		timerCallback : {},
		setCustomerDetails : a,
		TRIGGER : "click",
		_lastProductId : null,
		init : function(p) {
			if (Is.iOS) {
				this.TRIGGER = "touchstart"
			}
			i = this;
			i._datastore = Datastore;
			i
					.showStatusWindow(
							'Updating your offline data.  Won\'t be a sec.<span class="hidden-message" style="display:none"><br/><br/>If you are having problems loading then try <a href="/sell#reset">resetting your offline data</a>.</span>',
							null, function() {
								window.location = "/"
							});
			i._displayTimer = setTimeout(function() {
				$("#status-window .hidden-message").show("slow")
			}, 4000);
			i.resizeScreenElements();
			if ($.browser.opera
					|| ($.browser.msie && ($.browser.version == 6
							|| $.browser.version == 7 || $.browser.version == 9))
					|| ($.browser.mozilla && ($.browser.version == 2))) {
				i.showBrowserNotSupportedEverDialogue()
			}
			i._datastore
					.checkOfflineCapabilities(function() {
						if (!i._datastore.offline_supported) {
							if ($.browser.opera
									|| ($.browser.msie && ($.browser.version == 6
											|| $.browser.version == 7 || $.browser.version == 9))
									|| ($.browser.mozilla && ($.browser.version == 2))) {
								i.showBrowserNotSupportedEverDialogue()
							} else {
								i.showBrowserNotSupportedDialogue()
							}
						} else {
							$(i._datastore).bind("offline", function(t, s) {
								i.setStatusOffline()
							});
							$(i._datastore).bind("online", function(t, s) {
								i.setStatusOnline()
							});
							$(i._datastore).bind("sync", function(t, s) {
								i.setStatusSync()
							});
							$(i._datastore).bind("error", function(t, s) {
								i.setStatusError()
							});
							var r = function() {
								debug("DO GUI");
								i.initGui();
								i.bindHashChange();
								$(window).hashchange();
								$(i).unbind("key_escape")
							};
							var q = function() {
								debug("OPEN existing or new register");
								i.openExistingOrNewRegister(r)
							};
							$(".status-online").hide();
							$(".status-offline").hide();
							$(".status-error").hide();
							$(".status-sync").show();
							i._datastore.init(p, q)
						}
					})
		},
		cancelfunction : function() {
			debug("cancel!!")
		},
		openExistingOrNewRegister : function(r) {
			var q = this;
			var p = function(s) {
				if (typeof s == "undefined" || null === s) {
					q
							.showStatusWindow(
									'You dont have a register setup for this outlet! <a href="/setup/outlets_and_registers">Setup one now</a>',
									null, null)
				} else {
					debug("SAVE: Prepare register " + s.id);
					q.prepareRegister(s, r)
				}
			};
			if (typeof q._datastore.config.register_id === "undefined"
					|| q._datastore.config.register_id === null) {
				debug("OPEN: register");
				q.showStatusWindow("Opening register", null, null, 3000);
				q.openRegister(p)
			} else {
				debug("LOOKUP: register from datastore");
				q._datastore.getRegister(q._datastore.config.register_id,
						function(s) {
							q._datastore.config.register = s;
							if (s && typeof s != "undefined") {
								p(s)
							} else {
								q.showStatusWindow("Opening register", null,
										null, 3000);
								q.openRegister(p)
							}
						})
			}
		},
		addToPrintQueue : function(p) {
			this._printQueue.push(p)
		},
		printFromPrintQueue : function() {
			while (this._printQueue.length > 0) {
				var p = this._printQueue.shift();
				i.print(p)
			}
		},
		print : function(r) {
			var s = this, p = $("#receipt");
			if ("undefined" !== typeof r) {
				p.html(r)
			}
			r = p.html();
			debug("PRINT", r);
			try {
				if (Is.NativeClient) {
					VendPOS.NativeBridge.print(r)
				} else {
					if (Is.iOS) {
						window.setTimeout(window.print, 200)
					} else {
						window.print()
					}
				}
			} catch (q) {
				debug(q.message)
			}
		},
		doEmailDialogue : function(s) {
			var r = "";
			try {
				if (!isValidEmailAddress(i._datastore.register_sale.receipt_address)
						&& parseInt(i._datastore.register.email_receipt, 10) === 1) {
					var p = "";
					if (i._datastore.register_sale.customer) {
						p = i._datastore.register_sale.customer.email
					}
					r = prompt(
							"eReceipt? \n\rDo you want to email the receipt?  Save the trees. \n\rEnter customer email ",
							p);
					if (r === null) {
						r = ""
					}
					i._datastore.register_sale.receipt_address = r
				}
			} catch (q) {
			}
			if ("function" === typeof s) {
				s()
			}
		},
		loadReceiptTemplate : function(v) {
			if (i._datastore.register
					&& typeof i._datastore.register != "undefined") {
				var q = i._datastore.register.receipt.fields;
				$("#receipt").html("").append($("#receipt-template").html());
				$("#receipt #receipt-header").html(q.header);
				$("#invoice-title").html(q.label_invoice_title);
				$("#tax-label").html(q.label_tax);
				$("#subtotal-label").html(q.label_sub_total);
				$("#total-label").html(q.label_total);
				$(".topay-label").html(q.label_to_pay);
				if (i._datastore.config.account_state == "trialing") {
					$("#receipt #receipt-header").append(
							"<h2>DEMO SOFTWARE ONLY</h2>")
				}
				if (i._datastore.register_sale.customer) {
					var p = "";
					if (i._datastore.register_sale.customer.company_name) {
						p += '<div class="company">'
								+ i._datastore.register_sale.customer.company_name
								+ "</div>"
					} else {
						if (i._datastore.register_sale.customer.name) {
							p += '<div class="name">'
									+ i._datastore.register_sale.customer.name
									+ "</div>"
						}
					}
					p += '<div class="email">'
							+ i._datastore.register_sale.customer.email
							+ "</div>";
					if (i._datastore.register_sale.customer.physical_address1
							|| i._datastore.register_sale.customer.physical_address2
							|| i._datastore.register_sale.customer.physical_state
							|| i._datastore.register_sale.customer.physical_postcode
							|| i._datastore.register_sale.customer.physical_city) {
						p += '<div class="adr">';
						if (i._datastore.register_sale.customer.physical_address1) {
							p += '<div class="street-address">'
									+ i._datastore.register_sale.customer.physical_address1
									+ "</div>"
						}
						if (i._datastore.register_sale.customer.physical_address2) {
							p += '<div class="street-address-2">'
									+ i._datastore.register_sale.customer.physical_address2
									+ "</div> "
						}
						if (i._datastore.register_sale.customer.physical_suburb) {
							p += '<div class="locality">'
									+ i._datastore.register_sale.customer.physical_suburb
									+ "</div> "
						}
						if (i._datastore.register_sale.customer.physical_city) {
							p += '<span class="city">'
									+ i._datastore.register_sale.customer.physical_city
									+ "</span>, "
						}
						if (i._datastore.register_sale.customer.physical_state) {
							p += '<span class="region">'
									+ i._datastore.register_sale.customer.physical_state
									+ "</span>, "
						}
						if (i._datastore.register_sale.customer.physical_postcode) {
							p += '<span class="postal-code">'
									+ i._datastore.register_sale.customer.physical_postcode
									+ "</span>"
						}
						p += "</div>"
					}
					$("#receipt #receipt-customer").html(p)
				}
				$("#receipt #receipt-meta").html(
						'<span class="invoice-number">' + q.label_invoice + " "
								+ i._datastore.register_sale.invoice_number
								+ "</span>");
				var s;
				if (typeof i._datastore.register_sale.sale_date == "undefined"
						|| i._datastore.register_sale.sale_date === null) {
					var r = new Date();
					s = r.getUTCISODate()
				} else {
					s = i._datastore.register_sale.sale_date
				}
				var u = new Date(getDateFromFormat(s, "yyyy-MM-dd HH:mm:ss"))
						.getLocalHumanDate();
				$("#receipt #receipt-meta").append(
						'<span class="date">' + u + "</span>");
				$("#receipt #receipt-meta").append(
						'<span class="served-by">' + q.label_served_by + " "
								+ i._datastore.config.user_display_name
								+ " on " + i._datastore.register.name
								+ "</span>");
				$("#receipt #receipt-content").html("").append(
						$("#table_sale_items").clone()).append(
						$("#table-sale-totals").clone()).append(
						$("#table-payment-totals").clone()).append(
						i._receipt_buffer);
				i._receipt_buffer = "";
				$("#receipt #receipt-footer").html(q.footer);
				if (i._datastore.config.account_state == "trialing") {
					$("#receipt #receipt-footer").append(
							"<h2>DEMO SOFTWARE ONLY</h2>")
				}
				if (i._datastore.register_sale.note
						&& i._datastore.register.print_note_on_receipt
						&& i._datastore.register.print_note_on_receipt == 1) {
					var t = i._datastore.register_sale.note.replace(/\n/g,
							"<br/>");
					$("#receipt #receipt-note").html(t)
				}
				if (i._datastore.register.receipt_barcoded
						&& i._datastore.register.receipt_barcoded == 1) {
					$("#receipt #receipt-barcode img").attr(
							"src",
							"/images/barcodes/code128."
									+ i._datastore.register_sale.invoice_number
									+ ".20.2.2.png")
				} else {
					$("#receipt #receipt-barcode img").attr("src",
							"/images/spacer.png")
				}
			}
			if ("function" === typeof v) {
				v()
			}
		},
		startEPOSSocket : function(r, t) {
			i = this;
			var s = r.host;
			var q = r.port;
			var p = r.socket;
			if (p === "" || typeof p == "undefined") {
				p = "flash"
			}
			debug("host " + s + " port " + q + " socket_type " + p);
			debug("CONNECT!", i.socket, typeof i.socket, r);
			if (typeof s == "undefined" || typeof q == "undefined") {
				i
						.showStatusWindow(
								'<p>Your EFTPOS client is not set up correctly</p><p>For instructions on how to setup your DPS EFTPOS connection visit <a href="http://support.vendhq.com/entries/20781021-setting-up-dps-eftpos" target="_blank">Setting up DPS Eftpos</a></p>',
								function() {
									debug("OK")
								})
			}
			if (typeof i.socket !== "undefined") {
				debug("EPOS OBJECT EXISTS");
				t()
			} else {
				debug("New socket");
				if (p == "flash") {
					if (!jsXMLSocket.hasInstance("jsXMLSocket-DPS")) {
						$.extend(jsXMLSocket, {
							container : $("#socket"),
							path : "/js/jsxmlsocket-vend/jsXMLSocket.swf"
						});
						debug('create socket instance - "jsXMLSocket-DPS"');
						jsXMLSocket.createInstance("jsXMLSocket-DPS");
						$(window).bind("unload", function() {
							jsXMLSocket.destroyAllInstances()
						})
					}
					i.socket = jsXMLSocket.getInstance("jsXMLSocket-DPS");
					debug("Socket instance fetched", i.socket);
					i.socket.onReady = function() {
						debug("Connecting to " + s + ":" + q + "... ");
						this.connect(s, q)
					};
					this.socket.onError = function() {
						i.status = "EFTPOS_FAILURE";
						i
								.showStatusWindow(
										'<p>Could not connect to the EFTPOS client</p><p>For instructions on how to setup your DPS EFTPOS connection visit <a href="http://support.vendhq.com/entries/20781021-setting-up-dps-eftpos" target="_blank">Setting up DPS Eftpos</a></p>',
										function() {
											debug("OK")
										});
						debug("Connection failed.", error, r)
					};
					i.socket.onClose = function() {
						debug("Server disconnected.")
					};
					i.socket.onData = function(v) {
						var u = $(v).find("Ready");
						if (u.length === 1 && u.eq(0).text() === "1") {
							debug("Connected.");
							if ("function" === typeof t) {
								t()
							}
						} else {
							i.parseEPOSData(r || {}, v)
						}
					};
					if (i.socket.ready) {
						this.socket.connect(s, q)
					}
				} else {
					i.socket = new WebSocket("ws://" + s + ":" + q + "/test");
					i.socket.onopen = function() {
						debug("Socket Status: " + i.socket.readyState
								+ " (open)");
						if (i.socket.readyState != 3) {
							debug("Connected.");
							t()
						} else {
							i.status = "EFTPOS_FAILURE";
							i
									.showStatusWindow(
											'<p>Could not connect to the EFTPOS client</p><p>For instructions on how to setup your DPS EFTPOS connection visit <a href="http://support.vendhq.com/entries/20781021-setting-up-dps-eftpos" target="_blank">Setting up DPS Eftpos</a></p>',
											function() {
												debug("OK")
											});
							debug("Connection failed.")
						}
					};
					i.socket.onmessage = function(u) {
						debug('<p class="message">Received: ' + u.data);
						i.parseEPOSData(r, u.data)
					};
					i.socket.onclose = function() {
						debug('<p class="event">Socket Status: '
								+ i.socket.readyState + " (Closed)")
					};
					i.socket
							.send('<Message type="IdlePrompt" id="0000001234"><DisplayLine1>'
									+ i._datastore.config.retailer_name
									+ "</DisplayLine1><DisplayLine2>Vendhq.com</DisplayLine2></Message>")
				}
				debug("Setup.")
			}
		},
		shutdownEPOSSocket : function() {
			var q = this;
			var p = "flash";
			if (typeof q._datastore.eftpos_payment_type.config.socket != "undefined") {
				p = q._datastore.eftpos_payment_type.config.socket
			}
			if (p == "flash") {
				if (typeof q.socket !== "undefined") {
					debug("CLOSING SOCKET");
					if (!(q.socket instanceof window.jsXMLSocket)) {
						return
					}
					debug("SOCKET INFO", q.socket);
					q.socket.close();
					q.socket = null;
					delete q.socket
				}
			} else {
				if (typeof q.socket != "undefined") {
					q.socket.close();
					q.socket = null;
					delete q.socket
				}
			}
			debug("INFO: socket closed")
		},
		promptCheckSignature : function() {
			i._datastore.register_sale.payment_status = "SIGNATURE REQD";
			i._datastore.saveRegisterSale(i._datastore.register_sale);
			i.showStatusWindow("SIGNATURE OK Y/N?", function() {
				i.socket.send('<Message type="Button" id="'
						+ i._datastore.register_sale.id
						+ '"><Button>Yes</Button></Message>');
				debug("Signature OK")
			}, function() {
				i.socket.send('<Message type="Button" id="'
						+ i._datastore.register_sale.id
						+ '"><Button>No</Button></Message>');
				debug("Signature declined");
				i._datastore.register_sale.payment_status = "DECLINED";
				i._datastore.saveRegisterSale(i._datastore.register_sale);
				i.showStatusWindow("Signature declined", null, null, 2000)
			}, {
				okText : "Yes",
				cancelText : "No"
			})
		},
		parseEPOSData : function(H, I) {
			var A = this, B = A._datastore.current_register_sale_payment, r = '<?xml version="1.0" ?><dps>'
					+ I + "</dps>", y = $(r), C = $("Message", y).eq(0).attr(
					"type");
			if (typeof B == "undefined") {
				B = {
					amount : 0,
					register_id : A._datastore.config.register_id,
					payment_type_id : 5,
					register_sale_id : A._datastore.register_sale.id
				}
			}
			switch (C) {
			case "Transaction":
				debug("Transaction data received");
				var K = parseInt(y.find("Authorized").text(), 10), G = y.find(
						"ResponseText").text(), x = y.find("AmountPurchase")
						.text();
				B.amount = x;
				switch (G) {
				case "APPROVED":
				case "ACCEPTED":
				case "SIG ACCEPTED":
					debug("Transaction approved");
					var w = parseFloat(y.find("AmountRefund").text());
					if (!isNaN(w)) {
						B.amount = 0 - w
					}
					if (parseFloat(B.amount) === 0) {
						B.amount = parseFloat(y.find("AmountPurchase").text())
					}
					B.register_sale_id = A._datastore.register_sale.id;
					if (K === 1) {
						A._datastore.store.saveRegisterSalePayment(B,
								function() {
									A._datastore.updateRegisterSaleTotals(
											A._datastore.register_sale,
											function(L) {
												A.updateGUITotals(L);
												A.loadReceiptTemplate();
												A.status = "UPDATED_PAYMENT";
												A.closeSale()
											})
								})
					}
					A.shutdownEPOSSocket();
					A.hideStatusWindow("Accepted!", 200);
					break;
				case "ACCOUNT ERROR":
					A.showStatusWindow(G, function() {
						debug("cancelled");
						A.status = "UPDATED_PAYMENT";
						A.socket.send('<Message type="Button" id="'
								+ A._datastore.register_sale.id
								+ '"><Button>OK</Button></Message>');
						A.shutdownEPOSSocket()
					});
					break;
				case "TRANS CANCELLED":
					A.hideStatusWindow(G, 200, function() {
						A.status = "UPDATED_PAYMENT";
						A.socket.send('<Message type="Button" id="'
								+ A._datastore.register_sale.id
								+ '"><Button>OK</Button></Message>');
						A.shutdownEPOSSocket()
					});
					break;
				case "":
					debug("DPS EMPTY RESPONSE TEXT", I);
					break;
				default:
					debug("DPS UNKNOWN RESPONSE TEXT: " + G, I);
					A.hideStatusWindow(G, 200);
					break
				}
				break;
			case "Receipt":
				B.payment_type_id = parseInt(B.payment_type_id, 10);
				if ("undefined" === typeof B.payment_type) {
					var v = JSON.parse(window.localStorage
							.getItem("payment_types"));
					for ( var F = 0, D = v.length; F < D; F++) {
						var E = v[F];
						E.payment_type_id = parseInt(E.payment_type_id, 10);
						if (E.payment_type_id !== B.payment_type_id) {
							continue
						}
						B.payment_type = E
					}
				}
				if (!parseInt(B.payment_type.config.print, 10)) {
					break
				}
				A.status = "PRINTING_RECEIPT";
				var z = y.find("Receipt").text();
				z = z.replace(/(.{30})/g, "$1<br/>");
				z = z.replace(new RegExp(" ", "g"), "&nbsp;");
				z = '<span class="fixed-width-font eftpos-receipt">' + z
						+ "</span>";
				if (B.payment_type.config.print) {
					A._receipt_buffer = z
				}
				if (z.indexOf("ACCEPT&nbsp;WITH&nbsp;SIGNATURE") !== -1) {
					if (z.indexOf("CUSTOMER&nbsp;COPY") === -1) {
						if ((Number(A._datastore.register.print_receipt) || Is.NativeClient)
								&& B.payment_type.config.print) {
							debug("Print customer receipt for credit card signature");
							A.print(z)
						} else {
							debug("No printing required")
						}
					} else {
						debug(A._receipt_buffer)
					}
				}
				break;
			case "Status":
				G = y.find("Description").text();
				debug(G);
				break;
			case "Display":
				var s = y.find("Text1").text(), q = y.find("Text2").text(), u = y
						.find("Button1").text(), t = y.find("Button2").text();
				if (s == "SIGNATURE REQD" || s == "AWAITING ACCOUNT"
						|| s == "AWAITING PIN") {
					A._datastore.register_sale.payment_status = s;
					A._datastore.saveRegisterSale(A._datastore.register_sale);
					debug(s)
				}
				if ("SIGNATURE REQD" === s) {
					s = "SIGNATURE OK Y/N?";
					u = "Yes";
					t = "No"
				}
				var p = null, J = null;
				if (u && u.length > 0 && u in oc([ "Yes", "OK" ])) {
					p = function() {
						A.socket.send('<Message type="Button" id="'
								+ A._datastore.register_sale.id + '"><Button>'
								+ u + "</Button></Message>")
					}
				}
				if (u && u.length > 0 && u in oc([ "No", "Cancel" ])) {
					J = function() {
						A.socket.send('<Message type="Button" id="'
								+ A._datastore.register_sale.id + '"><Button>'
								+ u + "</Button></Message>")
					}
				}
				if ((t && t.length > 0 && t in oc([ "No", "Cancel" ]))) {
					J = function() {
						A.socket.send('<Message type="Button" id="'
								+ A._datastore.register_sale.id + '"><Button>'
								+ t + "</Button></Message>")
					}
				}
				A.showStatusWindow(s + "<br/>" + q, p, J, {
					okText : u.length ? u : "OK",
					cancelText : t.length ? t : "Cancel"
				});
				break
			}
		},
		registerWorkQueue : {
			_queue : [],
			addTask : function(p) {
				this._queue.push(p);
				debug("REGISTER WORK TASK ADDED - " + p, this._queue)
			},
			removeTask : function(t) {
				var p = [], u = false;
				for ( var s = 0, r = this._queue, q = r.length; s < q; s++) {
					if (t === r[s] && !u) {
						u = true
					} else {
						p.push(r[s])
					}
				}
				this._queue = p;
				debug("REGISTER WORK TASK REMOVED - " + t, this._queue)
			},
			hasTasks : function() {
				return (this._queue.length > 0)
			},
			clearTasks : function() {
				this._queue = [];
				debug("REGISTER WORK QUEUE CLEARED")
			}
		},
		bindRegisterForm : function(u) {
			var s = this, t = this._datastore, r = $("#product_search_product_sku"), p = $("#product_search_product_id"), q = $("#product_search_quantity");
			$("#btn-add-product").bind(Register.TRIGGER, function(v) {
				v.preventDefault();
				u.triggerHandler("forceSubmit")
			});
			r.bind("search", function(v) {
				if (13 === s.lastKeyPressCode) {
					return true
				}
				p.val("")
			});
			r.bind("keyup", function(v) {
				if (13 === s.lastKeyPressCode) {
					return true
				}
				if ("" === r.val()) {
					p.val("")
				}
			});
			u.bind("submit forceSubmit", function(y) {
				y.stopPropagation();
				y.preventDefault();
				var x = r.val(), w = p.val(), A = q.attr("disabled") ? 1
						: parseFloat(q.val());
				A = isNaN(A) ? 1 : A;
				if ("" === w || null === w) {
					w = undefined
				}
				if ("" === x || null === x) {
					x = undefined
				}
				if ("undefined" === typeof w && "undefined" === typeof x) {
					if (13 === s.lastKeyPressCode || "forceSubmit" === y.type) {
						w = s._lastProductId
					}
					if ("undefined" === typeof w) {
						return
					}
				}
				var v = "ADD: " + A + " x "
						+ ("undefined" !== typeof w ? w : x);
				s.registerWorkQueue.addTask(v);
				var z = {
					id : w,
					sku : x,
					quantity : A
				};
				t.saveRegisterSaleProductFromForm(z, t.register_sale, function(
						B) {
					if (null === B) {
						debug("No RegisterSaleProduct found");
						s.registerWorkQueue.removeTask(v);
						c({
							name : "",
							image : "/images/spacer.png",
							description : "",
							price : ""
						});
						return
					}
					s.updateSalesLine(B);
					c(B);
					s._lastProductId = B.product_id;
					s.registerWorkQueue.removeTask(v);
					if (s.registerWorkQueue.hasTasks()) {
						return
					}
					t.updateRegisterSaleTotals(t.register_sale, function(C) {
						s.updateGUITotals(C);
						s.loadReceiptTemplate()
					})
				});
				q.val(1);
				r.val("");
				p.val("");
				m();
				return false
			})
		},
		bindRegisterCheckoutForm : function(r) {
			var p = this;
			var q = function() {
				var t = {};
				var u = $(this).serializeArray();
				$.each(u, function(v, w) {
					if (w.name == "checkout[amount]") {
						t.amount = w.value
					}
					if (w.name == "checkout[payment_type]") {
						t.retailer_payment_type_id = w.value
					}
					t.register_sale_id = p._datastore.register_sale.id;
					t.register_id = p._datastore.config.register_id
				});
				var s = function(C) {
					t.payment_type = C;
					if (C.config) {
						if (typeof C.config != "object") {
							t.payment_type.config = JSON.parse(C.config)
						} else {
							t.payment_type.config = C.config
						}
					}
					var D = t.amount;
					var w = 0;
					$(p._datastore.register_sale.register_sale_products).each(
							function() {
								var P = this;
								w = P.tax_rate
							});
					D = roundNumber(D / (1 + parseFloat(w)), 2);
					t.payment_type_id = C.payment_type_id;
					p._datastore.current_register_sale_payment = t;
					if (t.payment_type_id == 5) {
						p._datastore.eftpos_payment_type = C;
						p._datastore
								.updateRegisterSaleTotals(
										p._datastore.register_sale,
										function(R) {
											var P = t.amount;
											debug("STARTING DPS SOCKECT COMMS");
											p
													.showStatusWindow(
															"Connecting to DPS Eftpos...",
															null,
															function() {
																p.status = "PAYMENT_CANCELLED";
																p
																		.hideStatusWindow(
																				"Cancelled",
																				2000);
																p
																		.shutdownEPOSSocket()
															});
											try {
												debug("Start EPOS");
												p
														.startEPOSSocket(
																t.payment_type.config,
																function() {
																	p
																			.showStatusWindow(
																					"Connecting to EFTPOS...",
																					null,
																					function() {
																						p.status = "PAYMENT_CANCELLED";
																						if (p.socket
																								&& p.socket.connected) {
																							p.socket
																									.send('<Message type="Button" id="'
																											+ p._datastore.register_sale.id
																											+ '"><Button>Cancel</Button></Message>');
																							p.socket
																									.send('<Message type="Button" id="'
																											+ p._datastore.register_sale.id
																											+ '"><Button>OK</Button></Message>');
																							debug("CANCEL BUTTON PUSHED");
																							p
																									.showStatusWindow("OK, cancelling... hold tight")
																						}
																					});
																	var U = "TXN"
																			+ p._datastore.register.invoice_prefix
																			+ p._datastore.register.invoice_sequence;
																	var T = "Purchase";
																	var V = "AmountPurchase";
																	if (parseFloat(P) > 0) {
																		T = "Purchase";
																		V = "AmountPurchase"
																	} else {
																		T = "Refund";
																		V = "AmountRefund";
																		P = 0 - parseFloat(P)
																	}
																	var S = '<Message type="Transaction" id="'
																			+ p._datastore.register_sale.id
																			+ '"><Account>1</Account> <TxnType>'
																			+ T
																			+ "</TxnType><TxnRef>"
																			+ U
																			+ "</TxnRef><"
																			+ V
																			+ ">"
																			+ P
																			+ "</"
																			+ V
																			+ "></Message>";
																	debug("SENDING "
																			+ S);
																	if (p.socket) {
																		p.socket
																				.send(S)
																	} else {
																		p
																				.hideStatusWindow()
																	}
																})
											} catch (Q) {
												return false
											}
										})
					} else {
						var F = function(P) {
							debug("do_payment_check", P);
							p._datastore.store.saveRegisterSalePayment(P, z)
						};
						var z = function() {
							p._datastore.updateRegisterSaleTotals(
									p._datastore.register_sale, A)
						};
						var A = function(P) {
							debug("Payment Complete");
							p.updateGUITotals(P);
							p.loadReceiptTemplate();
							p.status = "UPDATED_PAYMENT";
							p.closeSale()
						};
						if (typeof (t.payment_type.config.url) != "undefined"
								&& t.payment_type.config.url !== "") {
							debug("payment", t);
							window.location.hash = "";
							var v = t.payment_type.config.url
									+ (t.payment_type.config.url.indexOf("?") > -1 ? "&"
											: "?")
									+ "shortcode="
									+ p._datastore.register_sale.short_code
											.toUpperCase() + "&amount="
									+ t.amount + "&origin="
									+ escape(window.location.origin), O = v
									.split("/"), N = O[0] + "//" + O[2], E = {
								register_sale : p._datastore.register_sale,
								payment : t
							}, y, I, L;
							if (typeof p._datastore.register_sale.receipt_fields === "undefined") {
								p._datastore.register_sale.receipt_fields = {}
							}
							if (typeof t.response_data === "undefined") {
								t.response_data = {}
							}
							$("#checkout-form").hide();
							var x = function() {
								p.status = "UPDATED_PAYMENT";
								F(t)
							};
							p.showStatusWindow(
									"<h3>Was payment accepted?</h3>", x,
									function() {
									}, {
										okText : "Yes",
										cancelText : "No"
									});
							debug("Open payment gateway", v, E);
							I = $(window);
							var M = $('<div id="payment-gateway" class="ajax-popup-content-holder"></div>'), K = $('<div class="ajax-popup-content"><div class="box"><div class="head box-gradient-modal text-center"><h2 class="text">'
									+ t.payment_type.name
									+ '</h2></div><div class="content"></div></div></div>'), B = $('<span id="payment-gateway-cancel-button" class="modal-button-bar line"><button class="button-1 modal-button">Close</button></span>'), H = $('<iframe src="'
									+ v + '" />');
							K.css({
								marginTop : "-337px",
								width : "572px"
							});
							H.css({
								background : "#fff",
								display : "block",
								border : "1px solid #ccc",
								borderWidth : "0 0 1px",
								height : "557px",
								width : "550px"
							});
							B.css({
								display : "block",
								padding : "0 215px",
								margin : 0,
								width : "120px"
							}).find("button").css({
								display : "block",
								margin : "5px 0",
								width : "120px"
							}).bind(Register.TRIGGER, function() {
								M.remove();
								I.unbind("message")
							});
							K.find(".content").css("padding", 0).append(H)
									.append(B);
							$("#main-body > .content-inner")
									.append(M.append(K));
							y = H[0].contentWindow;
							M.show();
							var G = function() {
								debug("Payments API Clean up");
								if ("undefined" !== typeof I) {
									I.unbind()
								}
								if ("undefined" !== typeof M) {
									M.unbind();
									M.remove()
								}
								if ("undefined" !== typeof y) {
									y = undefined
								}
								if ("undefined" !== typeof $Register) {
									$Register.unbind("sale:new", G)
								}
								$(document.body).unbind("key_enter")
							};
							I.bind("unload", G);
							$Register.bind("sale:new", G);
							I
									.bind(
											"message",
											function(S) {
												var U = JSON
														.parse(S.originalEvent.data);
												debug("Message received: "
														+ U.step,
														S.originalEvent);
												if (U.success) {
													var T;
													if (typeof U.overload !== "undefined") {
														debug(
																"OVERLOAD - NEW",
																p._datastore.register_sale,
																t);
														$
																.extend(
																		p._datastore.register_sale,
																		U.overload.register_sale
																				|| {});
														$
																.extend(
																		t,
																		U.overload.payment
																				|| {});
														try {
															var V = U.overload.register_sale.customer;
															if ("object" === typeof V) {
																Datastore.register_sale.customer = V;
																Datastore.register_sale.customer_id = V.id;
																Register
																		.setCustomerDetails(V);
																Datastore
																		.setRegisterSaleCustomer(
																				Datastore.register_sale,
																				V,
																				V.customer_group_id,
																				null,
																				function() {
																				})
															}
														} catch (R) {
															debug(
																	"OVERLOAD - CUSTOMER",
																	R)
														}
														debug(
																"OVERLOAD - OLD",
																p._datastore.register_sale,
																t)
													}
													if (U.source && U.source_id) {
														t.source = U.source;
														t.source_id = U.source_id
													}
													$
															.extend(
																	p._datastore.register_sale.receipt_fields,
																	U.fields
																			|| {});
													$.extend(t.response_data,
															U.data || {});
													switch (U.step
															.toUpperCase()) {
													case "MANUAL":
														G();
														p
																.showStatusWindow(
																		"<h3>Was the payment successful?</h3>",
																		function() {
																			p.status = "UPDATED_PAYMENT";
																			debug(
																					"Payment: ACCEPT",
																					t,
																					p._datastore.register_sale);
																			F(t)
																		},
																		function() {
																			debug(
																					"Payment: DECLINE",
																					t,
																					p._datastore.register_sale)
																		},
																		{
																			title : "Security",
																			okText : "Yes",
																			cancelText : "No"
																		});
														break;
													case "PAYMENT":
														p._datastore.store
																.saveRegisterSalePayment(
																		U.payment,
																		function() {
																			debug(
																					"ADDED PAYMENT",
																					t)
																		});
														break;
													case "DATA":
														$("#status-window")
																.hide();
														E.step = "DATA";
														y
																.postMessage(
																		JSON
																				.stringify(E),
																		S.originalEvent.origin);
														break;
													case "AUTH":
														if ("undefined" === typeof U.hide
																|| true === U.hide) {
															M.hide()
														}
														var P = function() {
															p
																	.showStatusWindow(
																			"<h3>Do the signatures match?</h3>",
																			function() {
																				E.step = "ACCEPT";
																				y
																						.postMessage(
																								JSON
																										.stringify(E),
																								S.originalEvent.origin)
																			},
																			function() {
																				E.step = "DECLINE";
																				y
																						.postMessage(
																								JSON
																										.stringify(E),
																								S.originalEvent.origin)
																			},
																			{
																				title : "Security",
																				okText : "Yes",
																				cancelText : "No"
																			});
															var W = false;
															try {
																W = t.payment_type.config.print;
																if ("undefined" === typeof W
																		|| W === 0
																		|| W === false
																		|| W === "0"
																		|| W === "false") {
																	W = false
																} else {
																	if (W === 1
																			|| W === true
																			|| W === "1"
																			|| W === "true") {
																		W = true
																	}
																}
															} catch (X) {
																debug(X);
																W = false
															}
															if (W) {
																window
																		.setTimeout(
																				function() {
																					L = $(
																							"#receipt")
																							.clone();
																					L
																							.children(
																									"h1")
																							.eq(
																									0)
																							.text(
																									"Signature Slip");
																					$(
																							"#receipt-copy",
																							L)
																							.text(
																									"Merchant copy");
																					if (!$(
																							"#signature-box",
																							L).length) {
																						$(
																								"#receipt-content",
																								L)
																								.after(
																										'<div class="clearer"></div><p id="signature-message">I agree to pay above total amount according to card issuer agreement.</p><div id="signature-box"><div id="signature-guide">&nbsp;</div>Signature</div>')
																					}
																					var Z = $(
																							"#receipt-meta-extra",
																							L)
																							.empty();
																					$
																							.each(
																									p._datastore.register_sale.receipt_fields,
																									function(
																											aa,
																											ab) {
																										Z
																												.append("<p>"
																														+ aa
																														+ ": "
																														+ ab
																														+ "</p>")
																									});
																					var Y = $(
																							"#topay td",
																							L);
																					Y
																							.eq(
																									0)
																							.text(
																									"CREDIT PURCHASE");
																					Y
																							.eq(
																									1)
																							.text(
																									p._datastore.config.currency_name
																											+ roundNumber(t.amount));
																					Register
																							.print(L
																									.html())
																				},
																				0)
															}
														};
														var Q = U.amount === null ? parseFloat(t.amount)
																: parseFloat(U.amount);
														if (Q === parseFloat(t.amount)) {
															P()
														} else {
															if (Q > 0) {
																p
																		.showStatusWindow(
																				"<h3>Transaction has been partially approved for "
																						+ p._datastore.config.currency_name
																						+ U.amount
																						+ ". Would you like to continue?</h3>",
																				function() {
																					t.amount = U.amount;
																					P()
																				},
																				function() {
																					E.step = "DECLINE";
																					y
																							.postMessage(
																									JSON
																											.stringify(E),
																									S.originalEvent.origin)
																				},
																				{
																					title : "Partial Approval",
																					okText : "Yes",
																					cancelText : "No"
																				})
															} else {
																E.step = "DECLINE";
																y
																		.postMessage(
																				JSON
																						.stringify(E),
																				S.originalEvent.origin)
															}
														}
														break;
													case "ACCEPT":
														if (typeof t.payment_type.config.print !== "undefined"
																&& t.payment_type.config.print === "1") {
															T = new UI.PaymentReceipt(
																	"Approved",
																	p._datastore.config.currency_name
																			+ roundNumber(t.amount),
																	p._datastore.register_sale.receipt_fields);
															T.print()
														}
														p.status = "UPDATED_PAYMENT";
														debug(
																"Payment: ACCEPT",
																t,
																p._datastore.register_sale);
														F(t);
														if ("undefined" === typeof U.hide
																|| true === U.hide) {
															G()
														}
														break;
													case "DECLINE":
														if (typeof t.payment_type.config.print !== "undefined"
																&& t.payment_type.config.print === "1") {
															T = new UI.PaymentReceipt(
																	"Declined",
																	p._datastore.config.currency_name
																			+ roundNumber(t.amount),
																	p._datastore.register_sale.receipt_fields);
															T.print()
														}
														debug(
																"Payment: DECLINE",
																t,
																p._datastore.register_sale);
														if ("undefined" === typeof U.hide
																|| true === U.hide) {
															$("#status-window")
																	.hide();
															G()
														}
														break;
													case "EXIT":
														G();
														break
													}
												} else {
													if (typeof U.message !== "undefined") {
														alert(U.message)
													}
												}
											})
						}
						if (1 === parseInt(t.payment_type_id, 10)) {
							VendPOS.NativeBridge.openCashDrawer()
						}
						if (t.payment_type_id == 8) {
							var J = "";
							if (p._datastore.register_sale.customer
									&& p._datastore.register_sale.customer.email) {
								J = "&default_email="
										+ p._datastore.register_sale.customer.email
							}
							F = function(P) {
								var T = function() {
									p.status = "UPDATED_PAYMENT";
									p._datastore.store.saveRegisterSalePayment(
											P, z)
								};
								var R = function() {
								};
								p.showStatusWindow(
										"<h3>Was payment accepted?</h3>", T, R);
								if (Is.iOS) {
									var Q = window.location;
									var S = [ "square://pay?amount=", D,
											"&currency=USD&description=",
											p._datastore.config.retailer_name,
											J + "&callback=",
											Q.protocol + "//", Q.hostname,
											"/sell#!/payment%3Famount=",
											P.amount + "%26type=",
											P.payment_type_id ].join("");
									window.location = S
								}
							}
						} else {
							if (t.payment_type_id == 1) {
								A = function(Q) {
									if (parseFloat(Q.totals.total_to_pay) < parseFloat(0)) {
										p.status = "UPDATED_PAYMENT";
										t.amount = Q.totals.total_to_pay;
										t.id = guid();
										p._datastore.register_sale.last_payment_id = t.id;
										p._datastore.store
												.saveRegisterSalePayment(
														t,
														function() {
															p._datastore
																	.updateRegisterSaleTotals(
																			p._datastore.register_sale,
																			function(
																					S) {
																				p
																						.updateGUITotals(S);
																				p
																						.loadReceiptTemplate();
																				p.status = "UPDATED_PAYMENT";
																				if (Number(p._datastore.register.print_receipt)
																						|| Is.NativeClient) {
																					p._datastore.register_sale.receipt_printed = true;
																					if ($.browser.safari
																							&& typeof p._datastore.eftpos_payment_type != "undefined") {
																						p
																								.shutdownEPOSSocket();
																						window
																								.print()
																					} else {
																						window
																								.print()
																					}
																				}
																			})
														});
										var R = function() {
											p.closeSale()
										};
										var P = function() {
											p._datastore.register_sale.receipt_printed = false;
											p._datastore.store
													.deleteRegisterSalePayment(
															p._datastore.register_sale.last_payment_id,
															function() {
																debug("Current payments "
																		+ p._datastore.register_sale.register_sale_payments.length);
																for ( var T = 0; T < p._datastore.register_sale.register_sale_payments.length; T++) {
																	var S = p._datastore.register_sale.register_sale_payments[T];
																	debug(S);
																	if (S.id == p._datastore.register_sale.last_payment_id) {
																		p._datastore.register_sale.register_sale_payments
																				.splice(
																						T,
																						1)
																	}
																}
																p._datastore
																		.updateRegisterSaleTotals(
																				p._datastore.register_sale,
																				function(
																						U) {
																					debug(U);
																					p
																							.updateGUITotals(U);
																					p
																							.loadReceiptTemplate();
																					p.status = "UPDATED_PAYMENT";
																					p
																							.resizeScreenElements()
																				})
															})
										};
										p
												.showStatusWindow(
														"Issued customer change of <h3>"
																+ p._datastore.config.currency_name
																+ " "
																+ roundNumber(
																		0 - t.amount,
																		2)
																+ "?</h3>", R,
														P)
									} else {
										p.status = "UPDATED_PAYMENT";
										p
												.updateGUITotals(p._datastore.register_sale);
										p.loadReceiptTemplate();
										p.closeSale()
									}
								}
							} else {
								if (t.payment_type.config.confirm) {
									F = function(Q) {
										var P = Q.payment_type.config.prompt;
										if (!P) {
											P = "Was payment accepted?"
										}
										var S = function() {
											p.status = "UPDATED_PAYMENT";
											p._datastore.store
													.saveRegisterSalePayment(Q,
															z)
										};
										var R = function() {
										};
										p.showStatusWindow(
												"<h3>" + P + "</h3>", S, R)
									}
								}
							}
						}
						if (typeof (t.payment_type.config.url) === "undefined"
								|| t.payment_type.config.url === "") {
							p.setTenderPrompt();
							F(t)
						}
					}
				};
				p._datastore.getPaymentType(t.retailer_payment_type_id, s);
				return false
			};
			r.bind("submit", q)
		},
		bindCustomerSearchForm : function(q) {
			var p = this;
			q
					.bind(
							"submit",
							function() {
								var s = {};
								var r = $(this).find(
										"#customer_search_customer_code").val();
								p._datastore
										.getCustomerByCode(
												r,
												function(u) {
													if (u
															&& typeof u != "undefined") {
														p._datastore.register_sale.customer = u;
														p._datastore.register_sale.customer_id = u.id;
														p._datastore
																.setRegisterSaleCustomer(
																		p._datastore.register_sale,
																		u,
																		p._datastore.config.default_customer_group_id,
																		p._datastore.register.outlet_id,
																		p.refreshCustomerPricingDisplay);
														a(u);
														m()
													} else {
														var t = $("#create-customer");
														p
																.showStatusWindow(
																		"<h3>Customer not found</h3><p>Create a new customer?</p>",
																		function() {
																			var v = function(
																					x) {
																				window.location.hash = "";
																				t
																						.hide();
																				return false
																			};
																			$(p)
																					.unbind(
																							"key_escape")
																					.bind(
																							"key_escape",
																							v);
																			$(
																					".btn-cancel",
																					t)
																					.bind(
																							Register.TRIGGER,
																							v);
																			var w = r
																					.split(" ");
																			if (w.length > 1) {
																				$(
																						"#vend_customer_customer_contact_first_name",
																						t)
																						.val(
																								w[0]);
																				$(
																						"#vend_customer_customer_contact_last_name",
																						t)
																						.val(
																								w[1]);
																				$(
																						"#vend_customer_customer_code",
																						t)
																						.val(
																								w
																										.join(""))
																			} else {
																				$(
																						"#vend_customer_customer_code",
																						t)
																						.val(
																								r)
																			}
																			t
																					.show();
																			UI
																					.positionModalWindow(t)
																		},
																		function() {
																			$(
																					"#customer_search_customer_code")
																					.val(
																							"");
																			p
																					.hideStatusWindow()
																		})
													}
												});
								return false
							})
		},
		bindCustomerAddForm : function(q) {
			var p = this;
			q.bind("submit", function() {
				var v = $(this);
				var t = v.serializeArray();
				var u = {};
				$.each(t, function(w, x) {
					x.name = x.name.replace("vend_customer[customer_contact][",
							"");
					x.name = x.name.replace("vend_customer[", "");
					x.name = x.name.replace("]", "");
					u[x.name] = x.value
				});
				delete u.id;
				p._datastore.register_sale.customer = u;
				var r = function(y, x) {
					u = y.customer;
					window.location.hash = "";
					$("#create-customer").hide();
					var w = function() {
						p._datastore.setRegisterSaleCustomer(
								p._datastore.register_sale, u,
								p._datastore.config.default_customer_group_id,
								p._datastore.config.outlet_id, function(z) {
									p.loadReceiptTemplate();
									p.updateGUITotals(z)
								});
						a(u)
					};
					w();
					v.resetForm()
				};
				var s = function() {
					window.location.hash = "";
					$("#create-customer").hide();
					p.showStatusWindow(
							"There was a problem creating this customer", null,
							null, 3000)
				};
				$.ajax({
					url : "/api/customers",
					dataType : "json",
					data : JSON.stringify(u),
					type : "POST",
					success : r,
					error : s
				});
				return false
			})
		},
		bindCurrentSaleButton : function(p) {
			var q = this;
			p.bind(Register.TRIGGER, function(r) {
				r.preventDefault();
				q.showCurrentSale.call(q)
			})
		},
		bindCloseRegisterButton : function(p) {
			var q = this;
			p.bind(Register.TRIGGER, function(r) {
				r.preventDefault();
				q.closeRegister.call(q)
			})
		},
		bindSwitchRegisterButton : function(p) {
			var q = this;
			p.bind(Register.TRIGGER, function(r) {
				r.preventDefault();
				q.switchRegister.call(q)
			})
		},
		bindLookupSaleButton : function(p) {
			var q = this;
			p.bind(Register.TRIGGER, function(r) {
				r.preventDefault();
				q.openSavedSale.call(q)
			})
		},
		bindMakePaymentButton : function(p) {
			var q = this;
			p.bind(Register.TRIGGER, function(r) {
				r.preventDefault();
				if ("#pay" === window.location.hash) {
					q.showPaymentsDialogue();
					return
				}
				window.location.hash = "#pay"
			})
		},
		bindPaymentButtons : function() {
			var q = this;
			var p = $("#checkout_amount");
			if (p.attr("type") === "text") {
				p.unbind("blur").bind(
						"blur",
						function(t) {
							var v = $(this), s = (1.1).toLocaleString()
									.substring(1, 2), r = new RegExp(
									"[^0-9\\-\\" + s + "]+", "g"), u = v.val()
									.replace(r, "");
							u = parseFloat(u);
							u = isNaN(u) ? 0 : u;
							v.val(u)
						})
			}
			q._datastore.getPaymentTypes(function(r) {
				$("#checkout-form .payment-buttons div.secondary").empty();
				$(r).each(
						function(v, t) {
							var w = $("#payment_button_template .button-1")
									.clone();
							var u = t.name;
							var x = false;
							if (u.length > 15) {
								x = true;
								u = u.substring(0, 12) + "..."
							}
							w.val(t.id).html(u);
							if (x) {
								w.attr("title", t.name).addClass("tip")
							}
							$("#checkout-form .payment-buttons div.secondary")
									.append(w);
							if (x) {
								UI.bindTips()
							}
						});
				var s = $(".payment-button");
				if (s) {
					s.each(function() {
						var t = this;
						$(t).bind(Register.TRIGGER, function() {
							$("#checkout_payment_type").val($(t).val());
							return true
						})
					})
				}
			})
		},
		bindPaymentColourCoding : function(p) {
			var q = this;
			p.bind("keyup", function() {
				q.determinePaymentColours($(this));
				q.setTenderPrompt()
			});
			p.bind("change", function() {
				q.determinePaymentColours($(this));
				q.setTenderPrompt()
			})
		},
		setTenderPrompt : function() {
			var p = parseFloat($("#checkout-form .payment-totals-topay")
					.asNumber());
			if (p > 0) {
				$("label#prompt-for-tender").html("Amount tendered")
			} else {
				if (p === 0) {
					$("label#prompt-for-tender").html("Change/refund")
				} else {
					$("label#prompt-for-tender").html("Change/refund")
				}
			}
		},
		determinePaymentColours : function(p) {
			var q = p.val()
					- parseFloat($("#checkout-form .payment-totals-topay")
							.asNumber());
			if (q > 0) {
				$("#prompt-for-tender").html("Amount tendered");
				p.removeClass("to-refund");
				p.removeClass("no-payment-required");
				p.addClass("to-pay")
			} else {
				if (q === 0) {
					$("#prompt-for-tender").html("Amount tendered");
					p.removeClass("to-refund");
					p.addClass("no-payment-required");
					p.removeClass("to-pay")
				} else {
					$("#prompt-for-tender").html("Change/refund required");
					p.addClass("to-refund");
					p.removeClass("no-payment-required");
					p.removeClass("to-pay")
				}
			}
			if (parseFloat(p.val()) == parseFloat(0)
					&& parseFloat($("#checkout-form .payment-totals-topay")
							.html()) == parseFloat(0)) {
				p.removeClass("to-refund");
				p.removeClass("no-payment-required");
				p.removeClass("to-pay");
				return false
			}
		},
		bindClosePaymentButton : function(p) {
			var q = this, r = this._datastore, s = $("#checkout-form");
			p
					.bind(
							Register.TRIGGER,
							function(t) {
								t.preventDefault();
								if (r.register_sale.register_sale_payments.length > 0) {
									var u = confirm("You are returning back to the a sale with a payment applied to it.  \n\rThis sale has not been completed yet.  \n\rYou can return to this payment screen and complete the sale once after adding or removing items from the sale.\n\r\n\r");
									if (u) {
										window.location.hash = "";
										s.hide()
									}
								} else {
									window.location.hash = "";
									s.hide()
								}
							})
		},
		bindOpenDrawer : function(p) {
			var q = this;
			p.bind("click", function() {
				q.print("-- open drawer --");
				VendPOS.NativeBridge.openCashDrawer();
				return false
			})
		},
		bindBtnAddCustomer : function(p) {
			var q = this;
			p.bind("click", function() {
				var r = function() {
					window.location.hash = "";
					$("#create-customer").hide();
					return false
				};
				$(q).unbind("key_escape");
				$(q).bind("key_escape", r);
				$("#create-customer .btn-cancel").bind(Register.TRIGGER, r);
				var s = $("#create-customer");
				s.show();
				UI.positionModalWindow(s)
			});
			if (Is.iOS && "undefined" !== typeof $.fn.jScrollTouch) {
				$("#create-customer .edit_details").jScrollTouch()
			}
		},
		bindResync : function(p) {
			var q = this;
			p.find(".icon").bind("click", function() {
				q.workerAgentActions()
			});
			p.find(".status-text").bind("click", function() {
				var r = $("#reset-data");
				r.show();
				UI.positionModalWindow(r);
				var s = function() {
					r.hide();
					window.location.hash = "";
					return false
				};
				$(q).unbind("key_escape").bind("key_escape", s);
				r.find(".btn-cancel").bind(Register.TRIGGER, s);
				return true
			})
		},
		bindSaveSaleButton : function(p) {
			p.bind(Register.TRIGGER, this.parkSale)
		},
		bindSaveSaleNoteButton : function(p) {
			p.bind(Register.TRIGGER, this.saveSaleNote)
		},
		bindCustomerActionLayby : function(p) {
			var q = this, r = this._datastore;
			p
					.bind(
							Register.TRIGGER,
							function(s) {
								s.preventDefault();
								if (!r.register_sale.customer
										|| r.register_sale.customer.id == r.config.default_customer_id) {
									alert('There is no customer for this sale.  Lookup a customer in the "Customer Code" field.');
									return false
								}
								q.laybySale.call(q)
							})
		},
		bindCustomerActionOnAccount : function(p) {
			var q = this, r = this._datastore;
			p
					.bind(
							Register.TRIGGER,
							function(s) {
								s.preventDefault();
								if (!r.register_sale.customer
										|| r.register_sale.customer.id == r.config.default_customer_id) {
									alert('There is no customer for this sale.  Lookup a customer in the "Customer Code" field.');
									return false
								}
								q.onaccountSale.call(q)
							})
		},
		bindVoidSale : function(p) {
			var q = this, r = this._datastore;
			p
					.bind(
							Register.TRIGGER,
							function(s) {
								s.preventDefault();
								if (r.register_sale.status == "OPEN"
										|| r.register_sale.status == "SAVED") {
									var t = confirm("This will clear the current sale.  All items and payments on this sale will be lost.  \n\rAre you sure?");
									if (t) {
										q.voidSale.call(q)
									}
								} else {
									r.getNewRegisterSale.call(q, q.loadSale)
								}
							})
		},
		bindCloseSale : function(p) {
			var q = this, t = this._datastore, s = $("#btn-close"), r = $("#btn-void");
			p.bind(Register.TRIGGER, function(u) {
				u.preventDefault();
				s.hide();
				r.show();
				q.saveSaleForUpload(t.register_sale.status, false, function() {
					t.getNewRegisterSale(function(v) {
						q.loadSale(v);
						t.uploadRegisterSales()
					})
				})
			})
		},
		openSavedSale : function() {
			var q = [ "SAVED", "LAYBY", "ONACCOUNT" ];
			var p = $("#select-sale");
			p.find("#register-sale-list > tbody").html(
					'<tr><td colspan="99"><h3>Loading sales</h3></td></tr>');
			$("#sell").hide();
			$("#close-register").hide();
			$("#btn-current-sale").removeClass("current");
			$("#btn-close-register").removeClass("current");
			$("#btn-lookup-sale").addClass("current");
			p.show();
			i._datastore.refreshRegisterSales(q, "1970-01-01", function(s) {
				var r = [ "SAVED", "LAYBY", "ONACCOUNT" ];
				i.showSelectSaleDialogue(s, i.loadSale)
			})
		},
		loadSale : function(s) {
			$("#close-register").hide();
			$("#view-object").hide();
			if (Is.NativeClient) {
				VendPOS.util.NativeBridge.setNavigation("current")
			}
			if (s.status === "OPEN" || s.status === "SAVED") {
				$("#btn-close").hide();
				$("#btn-void").show()
			} else {
				$("#btn-close").show();
				$("#btn-void").hide()
			}
			if (s
					&& (s.status == "OPEN" || s.status == "SAVED"
							|| s.status == "LAYBY" || s.status == "ONACCOUNT")) {
				i._datastore.register_sale_id = s.id;
				i._datastore.config.register_sale_id = s.id;
				i._datastore.register_sale = s;
				i.clearRegisterSaleProducts();
				var r = $("#btn-save-sale"), u = $("#btn-close"), p = $("#btn-void"), q = $(
						".customer-editable", "#customer_details");
				if (r.length) {
					r = r.parent();
					if (s.status === "OPEN" || s.status === "SAVED") {
						q.show();
						if (r.hasClass("button-inactive")) {
							r.removeClass("button-inactive")
						}
						p.show();
						u.hide()
					} else {
						q.hide();
						if (!r.hasClass("button-inactive")) {
							r.addClass("button-inactive")
						}
						p.hide();
						u.show()
					}
				}
				var t = function(v) {
					debug("update customer");
					if (v && typeof v !== "undefined") {
						i._datastore.register_sale.customer = v;
						i._datastore.register_sale.customer_id = v.id;
						a(v)
					} else {
						a({
							name : "",
							company_name : "",
							code : "WALKIN",
							balance : 0
						})
					}
					i.updateGUITotals(i._datastore.register_sale);
					i.loadReceiptTemplate();
					$("#sell").show(0, function() {
						i.resizeScreenElements()
					})
				};
				i._datastore
						.saveConfig(
								i._datastore.config,
								function() {
									i._datastore
											.updateRegisterSaleTotals(
													s,
													function(v) {
														i._datastore.store
																.getRegisterSaleProducts(
																		v,
																		function(
																				w) {
																			debug("update lines");
																			i
																					.updateCurrentSaleItems(w);
																			if (i._datastore.register_sale.customer_id !== null) {
																				i._datastore
																						.getCustomer(
																								i._datastore.register_sale.customer_id,
																								t)
																			} else {
																				t(null)
																			}
																		})
													})
								})
			}
		},
		saveSaleNote : function(p) {
			i.showSaveSaleDialogue(i._datastore.register_sale, function(r, q) {
				if (typeof r != "undefined") {
					i._datastore.register_sale.note = r.substr(0, 2000)
				}
				m();
				i._datastore.saveRegisterSale(i._datastore.register_sale,
						function() {
							i.loadReceiptTemplate(function() {
								if ("function" === typeof p) {
									p()
								}
							})
						})
			});
			return false
		},
		saveSaleForUpload : function(s, r, t) {
			var p = function() {
				i._datastore.register_sale.sync_status = "UPLOAD";
				i._datastore.saveRegisterSale(i._datastore.register_sale,
						function() {
							i._datastore.config.register_sale_id = null;
							if ("function" === typeof t) {
								t()
							}
						})
			};
			i._datastore.register_sale.register_id = i._datastore.config.register_id;
			if (i._datastore.register_sale.status == "OPEN") {
				var q = new Date();
				i._datastore.register_sale.sale_date = q.getUTCISODate()
			}
			if (i._datastore.register_sale.status != "CLOSED"
					&& i._datastore.register_sale.status != "VOIDED") {
				i._datastore.register_sale.status = s
			}
			if (r) {
				i.showSaveSaleDialogue(i._datastore.register_sale, function(v,
						u) {
					if (typeof v != "undefined") {
						i._datastore.register_sale.note = v.substr(0, 2000)
					}
					i.loadReceiptTemplate(function() {
						p()
					})
				})
			} else {
				p()
			}
			return false
		},
		parkSale : function() {
			var r = $("#btn-save-sale");
			if (r.length && r.parent().hasClass("button-inactive")) {
				return
			}
			var q = "SAVED";
			if ((typeof i._datastore.register_sale.user_name == "undefined")
					|| i._datastore.register_sale.user_name === ""
					|| i._datastore.register_sale.status == "OPEN") {
				i._datastore.register_sale.user_name = i._datastore.config.user_name
			}
			var p = i._datastore.register.ask_for_note_on_save;
			if (p == 1 || p == 2) {
				p = true
			} else {
				p = false
			}
			i.saveSaleForUpload(q, p, i.completeAndNewSale);
			return false
		},
		onaccountSale : function() {
			var r = "ONACCOUNT";
			var q = function() {
				var s = i._datastore.register.ask_for_note_on_save;
				if (s == 1 || s == 2) {
					s = true
				} else {
					s = false
				}
				i.saveSaleForUpload(r, s, function() {
					i.completeAndNewSale();
					if (Number(i._datastore.register.print_receipt)
							|| Is.NativeClient) {
						Register.print()
					}
				})
			};
			if (i._datastore.register_sale.status == "OPEN") {
				var p = new Date();
				i._datastore.register_sale.sale_date = p.getUTCISODate()
			}
			if ((typeof i._datastore.register_sale.user_name == "undefined")
					|| i._datastore.register_sale.user_name === ""
					|| i._datastore.register_sale.status == "OPEN") {
				i._datastore.register_sale.user_name = i._datastore.config.user_name
			}
			i.updateGUITotals(i._datastore.register_sale);
			window.location.hash = "";
			i.loadReceiptTemplate(function() {
				i.doEmailDialogue(q);
				$("#checkout-form").hide()
			});
			return false
		},
		laybySale : function() {
			var r = "LAYBY";
			var p = new Date();
			if (i._datastore.register_sale.status == "OPEN") {
				i._datastore.register_sale.sale_date = p.getUTCISODate()
			}
			if ((typeof i._datastore.register_sale.user_name == "undefined")
					|| i._datastore.register_sale.user_name === ""
					|| i._datastore.register_sale.status == "OPEN") {
				i._datastore.register_sale.user_name = i._datastore.config.user_name
			}
			window.location.hash = "";
			i.updateGUITotals(i._datastore.register_sale);
			i.loadReceiptTemplate();
			var q = function() {
				var s = i._datastore.register.ask_for_note_on_save;
				if (s == 1 || s == 2) {
					s = true
				} else {
					s = false
				}
				i.saveSaleForUpload(r, s, function() {
					i.completeAndNewSale();
					if (Number(i._datastore.register.print_receipt)
							|| Is.NativeClient) {
						Register.print()
					}
				})
			};
			i.doEmailDialogue(q);
			$("#checkout-form").hide();
			return false
		},
		closeSale : function() {
			var s = new Date();
			i._datastore._doingSync = true;
			window.location.hash = "";
			if (typeof i._datastore.register_sale.totals === "undefined"
					|| i._datastore.register_sale.totals.total_price === 0
					|| parseFloat(i._datastore.register_sale.totals.total_to_pay) !== parseFloat(0)) {
				if (typeof i._datastore.register_sale.totals === "undefined"
						|| parseFloat(i._datastore.register_sale.totals.total_price) === parseFloat(0)) {
					debug("Oops! I can not close this sale!\n\nThere are no items in the sale.")
				} else {
					if (parseFloat(i._datastore.register_sale.totals.total_to_pay) !== parseFloat(0)) {
						debug("Oops! I can not close this sale!\n\nThere is an outstanding balance on this sale.\nPlease refund or accept payment so the total to pay is 0, plus make sure there are items in the sale.")
					} else {
						debug("Oops! I can not close this sale!")
					}
				}
				i.updateGUITotals(i._datastore.register_sale);
				i.status = "ACCEPTED_PAYMENT";
				return false
			}
			if (((i._datastore.register_sale.status != "SAVED"
					&& i._datastore.register_sale.status != "LAYBY" && i._datastore.register_sale.status != "ONACCOUNT"))
					|| typeof i._datastore.register_sale.sale_date == "undefined") {
				i._datastore.register_sale.sale_date = s.getUTCISODate()
			}
			if ((typeof i._datastore.register_sale.user_name == "undefined")
					|| i._datastore.register_sale.user_name === ""
					|| i._datastore.register_sale.status == "OPEN"
					|| i._datastore.register_sale.status == "SAVED") {
				i._datastore.register_sale.user_name = i._datastore.config.user_name
			}
			i.status = "CLOSING_SALE";
			var r = function() {
				w();
				i.completeAndNewSale()
			};
			var q = function(x) {
				debug(x);
				r()
			};
			var p = function(x) {
				$.ajax({
					type : "POST",
					url : x,
					data : {
						register_sale : i._datastore.register_sale
					},
					success : q,
					error : q,
					dataType : "json"
				})
			};
			var w = function() {
				if ((Number(i._datastore.register.print_receipt) || Is.NativeClient)
						&& !i._datastore.register_sale.receipt_printed) {
					if ($.browser.safari
							&& typeof i._datastore.eftpos_payment_type != "undefined") {
						i.shutdownEPOSSocket();
						Register.print()
					} else {
						Register.print()
					}
				}
				i._datastore._doingSync = false
			};
			var v = function() {
				debug("INFO: Complete sale");
				i._datastore
						.closeRegisterSale(
								i._datastore.register_sale,
								function() {
									var x = false;
									debug("INFO: Complete and new sale");
									if (i._datastore.register.ask_for_note_on_save == 2) {
										x = true
									} else {
										x = false
									}
									i
											.saveSaleForUpload(
													i._datastore.register_sale.status,
													x,
													function() {
														debug("INFO: Print receipt");
														if (typeof i._datastore.config.callbacks != "undefined"
																&& typeof i._datastore.config.callbacks.close_sale != "undefined"
																&& i._datastore.config.callbacks.close_sale.url !== "") {
															p(i._datastore.config.callbacks.close_sale.url)
														} else {
															r()
														}
													})
								})
			};
			var u = function() {
				i.resetProductEntry();
				i.status = "CLOSED_SALE";
				i._datastore.saveRegisterSale(i._datastore.register_sale, v)
			};
			var t = function() {
				try {
					i.updateGUITotals(i._datastore.register_sale);
					i.loadReceiptTemplate();
					i.doEmailDialogue(u)
				} catch (x) {
					u()
				}
			};
			$("#checkout-form").hide();
			i._datastore.config.register_sale_id = null;
			i._datastore.saveConfig(i._datastore.config, function() {
				i._datastore.saveRegisterSale(i._datastore.register_sale, t)
			});
			$Register.triggerHandler("sale:close");
			return false
		},
		voidSale : function() {
			var p = new Date();
			if (i._datastore.register_sale.status == "OPEN") {
				i._datastore.register_sale.sale_date = p.getUTCISODate()
			}
			i._datastore.register_sale.status = "VOIDED";
			i._datastore.register_sale.sync_status = "UPLOAD";
			i.loadReceiptTemplate();
			i._datastore.saveRegisterSale(i._datastore.register_sale,
					i.completeAndNewSale);
			i.resetProductEntry();
			i.showStatusWindow("Voided", null, null, 500);
			$Register.triggerHandler("sale:void");
			return false
		},
		switchRegister : function(q) {
			if (!q) {
				q = "This will switch the register you are currently signed into.  \n\rAll new sales and payment amounts will be applied to the new register.  This may affect your cash counts in your cash drawer.  \n\rAre you sure?"
			}
			var r = confirm(q);
			if (r) {
				i._datastore.register = null;
				i._datastore.register_id = null;
				i._datastore.config.register_id = null;
				i._datastore.config.register_sale_id = null;
				var p = function() {
					i.showCurrentSale();
					i.openExistingOrNewRegister(function() {
						i._datastore.getNewRegisterSale(function() {
						})
					})
				};
				debug("*** Switching register");
				i._datastore.saveConfig(i._datastore.config, p)
			}
			return false
		},
		showCurrentSale : function() {
			$("#close-register").hide();
			$("#select-sale").hide();
			if (typeof i._datastore.config.register_id === "undefined"
					|| i._datastore.config.register_id === null) {
				i.openExistingOrNewRegister(function() {
					i.hideStatusWindow()
				})
			}
			$("#sell").show();
			$("#btn-current-sale").addClass("current");
			$("#btn-close-register").removeClass("current");
			$("#btn-lookup-sale").removeClass("current")
		},
		closeRegister : function() {
			i.showCloseRegisterDialogue()
		},
		openDrawer : function() {
			var p = this;
			p.print("-- open drawer --");
			VendPOS.NativeBridge.openCashDrawer()
		},
		initGui : function() {
			debug("INIT: initGui");
			var t = this;
			t.loadReceiptTemplate();
			t.updateUserAndOutletname();
			t.lastKeyPressCode = null;
			this.bindRegisterForm($("#register-input"));
			t.bindRegisterCheckoutForm($("#register-checkout-form"));
			t.bindCustomerSearchForm($("#customer-search-form"));
			t.bindCustomerAddForm($("#create-customer form"));
			t.bindPaymentButtons();
			t.bindCurrentSaleButton($("#btn-current-sale"));
			t.bindCloseRegisterButton($("#btn-close-register"));
			t.bindSwitchRegisterButton($("#btn-switch-register"));
			t.bindBtnAddCustomer($("#btn-add-customer"));
			t.bindLookupSaleButton($("#btn-lookup-sale"));
			t.bindMakePaymentButton($("#btn-make-payment"));
			t.bindClosePaymentButton($("#btn-close-payment"));
			t.bindPaymentColourCoding($("#checkout_amount"));
			t.bindSaveSaleButton($("#btn-save-sale"));
			t.bindSaveSaleNoteButton($("#btn-save-note"));
			t.bindResync($("#btn-resync"));
			t.bindVoidSale($("#btn-void"));
			t.bindCloseSale($("#btn-close"));
			t.bindBtnDiscount($("#btn-discount"));
			t.bindBtnDiscountSale($("#btn-discount-sale"));
			t.bindBtnClosePrint($("#btn-close-print"));
			t.bindCustomerActionOnAccount($("#btn-save-onaccount"));
			t.bindCustomerActionLayby($("#btn-save-layby"));
			t.setupProductAutocomplete();
			t.setupCustomerAutocomplete();
			UI.bindStatusTips();
			t.bindKeyListeners();
			if (Is.iOS && "undefined" !== typeof $.fn.jScrollTouch) {
				$("#sales_items_container").jScrollTouch();
				$("#register_list_container").jScrollTouch();
				$("#sales_list_container").jScrollTouch();
				$("#create-customer .edit_details").jScrollTouch()
			}
			t.setupSwtichRegisterButton();
			$("input, textarea, select").focus(function() {
				t.selectedInput = this
			});
			t.resetProductEntry();
			var s = $("#register-display a.delete_product");
			s.each(function() {
				t.registerDeleteSaleItem(this)
			});
			var r = function() {
				var u = setTimeout(r, 300000);
				t.workerAgentActions();
				u = null
			};
			var q = function() {
				var u = setTimeout(q, 5000);
				t._datastore
						.checkForDuplicateBrowserSessionChange(function(v) {
							if (v) {
								var w = alert("You have just opened the sell screen in another window or tab!\n\rYou can only have the sell screen open in one window at a time.\n\rIf this window does not close automatically, please close this window manually.\n\r\n\r");
								t
										.showStatusWindow('You have multiple "sell" windows open. Please close this window or tab in your browser');
								window.close()
							}
						});
				u = null
			};
			if (t._datastore.outlet_changed) {
				t
						.switchRegister("Your outlet has changed.  Do you want to switch to a register at this outlet?")
			}
			$(window).resize(function() {
				t.resizeScreenElements()
			});
			m();
			var p = function(u) {
				r();
				q();
				t.hideStatusWindow();
				u()
			};
			p(t.checkEPOSRunning)
		},
		checkEPOSRunning : function() {
			var p = i._datastore
					.getPaymentTypes(function(q) {
						$(q)
								.each(
										function() {
											var s = this;
											if (s.payment_type_id == 5) {
												i._datastore.eftpos_payment_type = s;
												var r = {};
												r.amount = parseFloat(0);
												r.source = "";
												r.source_id = "";
												r.retailer_payment_type_id = s.id;
												r.payment_type_id = 5;
												r.register_sale_id = i._datastore.register_sale.id;
												r.register_id = i._datastore.config.register_id;
												i._datastore.current_register_sale_payment = r;
												if (i._datastore.register_sale.payment_status == "SIGNATURE REQD"
														|| i._datastore.register_sale.payment_status == "AWAITING ACCOUNT"
														|| i._datastore.register_sale.payment_status == "AWAITING PIN") {
													i
															.startEPOSSocket(
																	s.config,
																	function() {
																		if (i._datastore.register_sale.payment_status == "SIGNATURE REQD") {
																			i
																					.promptCheckSignature()
																		} else {
																			var t = function() {
																				i.socket
																						.send('<Message type="Button" id="'
																								+ i._datastore.register_sale.id
																								+ '"><Button>Cancel</Button></Message>');
																				i._datastore.register_sale.payment_status = "CANCELLED";
																				i._datastore
																						.saveRegisterSale(i._datastore.register_sale)
																			};
																			i
																					.showStatusWindow(
																							i._datastore.register_sale.payment_status,
																							null,
																							t)
																		}
																	})
												}
											}
										})
					})
		},
		resizeScreenElements : function() {
			var q = 484;
			if (Is.NativeClient) {
				q = 626
			} else {
				if (Is.iOS) {
					q = 480
				}
			}
			var p = q - $("#register-checkout").height()
					- $("#scanned-product").height();
			if (p < 80) {
				p = 80
			}
			$("#sales_items_container").height(p)
		},
		setupSwtichRegisterButton : function() {
			this._datastore.getRegisters(i._datastore.config.outlet_id,
					function(p) {
						if (p.length > 1) {
							$("#btn-switch-register").show();
							$("#register_name .name").hide()
						}
					})
		},
		setupProductAutocomplete : function() {
			var x = this, q = this._datastore;
			var u = $("#product_search_product_id"), t = $("#product_search_product_sku"), p = $("#product_search_quantity");
			t.bind("keydown", function(A) {
				x.lastKeyPressCode = A.keyCode
			});
			t.bind("webkitspeechchange", function() {
				t.trigger("keydown")
			});
			var w = function(E) {
				var A = [], C = 0, B = E.length, D;
				debug("Autocomplete: Found " + E.length + " matches from term");
				for (; C < B; C++) {
					D = E[C];
					A[A.length] = {
						data : D,
						value : D.name,
						result : ("function" === typeof z.formatResult) ? z
								.formatResult(D, D.name) : D.name
					}
				}
				return A
			};
			var y = function(C) {
				var B = 0;
				culture_info = q.culture_info;
				culture_info.colorize = 0;
				if (C.display_retail_price_tax_inclusive) {
					B = C.price + C.tax
				} else {
					B = C.price
				}
				var A = $("<div>" + B + "</div>").formatCurrency(culture_info);
				return [
						'<span class="name">' + C.name + "</span>",
						'<span class="price">' + A.html() + "</span>",
						'<div class="details">',
						'<span class="handle">Handle: ' + C.handle
								+ " </span> / ",
						'<span class="sku">SKU: ' + C.sku + " </span>",
						"</div>" ].join("")
			};
			var v = function(A) {
				return A.id
			};
			var s = function(A, B) {
				if ("undefined" === typeof B) {
					return
				}
				if ("undefined" === typeof B.product_id) {
					B.product_id = B.id
				}
				t.val(B.sku);
				u.val(B.id);
				c(B);
				if ((9 === i.lastKeyPressCode)) {
					p.focus().select();
					A.preventDefault();
					debug("**** DON'T ADD PRODUCT")
				} else {
					debug("**** ADD PRODUCT")
				}
			};
			var r = function() {
				q.findProductByTerm.apply(q, arguments)
			};
			var z = {
				minChars : 2,
				width : 420,
				max : 100,
				scroll : true,
				scrollHeight : 300,
				cacheLength : 0,
				matchContains : "word",
				autoFill : false,
				delay : 200,
				parse : w,
				formatItem : y,
				formatResult : v
			};
			t.autocomplete(r, z);
			t.result(s)
		},
		setupCustomerAutocomplete : function() {
			var t = this;
			var s;
			var r;
			var q;
			s = function(v, w) {
				debug("lookup customer from server", v);
				t._datastore.findCustomerByTerm(v, w)
			};
			$("#customer_search_customer_code").keydown(function(v) {
				t.lastKeyPressCode = v.keyCode
			});
			var u = function(z) {
				var v = [];
				debug("found " + z.length + " matches from term");
				for ( var x = 0, w = z.length; x < w; x++) {
					var y = z[x];
					v[v.length] = {
						data : y,
						value : y.name,
						result : p.formatResult && p.formatResult(y, y.name)
								|| y.name
					}
				}
				return v
			};
			r = function(w) {
				var v = w;
				return '<span class="name">' + v.name
						+ '</span><span class="code">(' + v.customer_code
						+ ')</span><br/><span class="company_name">'
						+ v.company_name + "</span>"
			};
			q = function(w) {
				var v = w;
				return v.customer_code
			};
			var p = {
				minChars : 4,
				width : 320,
				max : 100,
				highlight : false,
				scroll : true,
				scrollHeight : 300,
				cacheLength : 0,
				matchContains : "word",
				autoFill : false,
				delay : 300,
				parse : u,
				formatItem : r,
				formatResult : q
			};
			$("#customer_search_customer_code").autocomplete(s, p);
			$("#customer_search_customer_code").result(function(v, x, w) {
				if ((t.lastKeyPressCode === 9)) {
					$("#customer-search-form").submit()
				} else {
					if ((t.lastKeyPressCode !== 13)) {
						$("#customer-search-form").submit()
					} else {
						if (Is.iOS) {
							$("#customer-search-form").submit()
						}
					}
				}
			})
		},
		resetProductEntry : function() {
			var p = this;
			$("#product_search_quantity").val(1);
			$("#btn-set-price .button-face").html("$");
			$("#product_search_product_sku").val("");
			$("#product_search_product_sku_id").val("");
			m();
			c({
				name : "",
				image : "/images/spacer.png",
				description : "",
				price : ""
			});
			$("#product_price_quantity").show();
			p._datastore.register_sale.is_new = true
		},
		workerAgentActions : function() {
			debug("WORKER: in worker agent");
			var p = this;
			p._datastore._doingSync = false;
			if (p._datastore._doingSyncProducts
					|| p._datastore._doingSyncCustomers) {
				p._datastore._doingSync = true
			}
			if (p._datastore._doingSync === false) {
				var q = function() {
					var r = function() {
						debug("WORKER: Refresh Local datastore");
						p._datastore.refreshLocalDataStore(s)
					};
					var s = function() {
						debug("WORKER: Refreshed local data store")
					};
					debug("WORKER: check for any sales to upload in worker QUEUE");
					p._datastore.uploadRegisterSales(r)
				};
				q();
				q = null
			} else {
				debug("SKIPPING WORKER AGENT, synch in progress")
			}
		},
		setStatusOffline : function() {
			$(".status-online").hide();
			$(".status-sync").hide();
			$(".status-error").hide();
			$(".status-offline").show();
			$(".status-offline").qtip("toggle", true);
			var p = setTimeout(function() {
				$(".status-offline").qtip("hide")
			}, 4000)
		},
		setStatusError : function() {
			$(".status-online").hide();
			$(".status-sync").hide();
			$(".status-offline").hide();
			$(".status-error").show();
			$(".status-error").qtip("toggle", true);
			var p = '<strong>THERE WAS A SERIOUS PROBLEM!</strong><br/>We tried to upload a sale to the server and it failed. This sale has not been saved.<br/><a href="#showerrors" id="showerrors">Show errors</a>';
			$(".status-error").qtip("option", "content.text", p);
			$("#showerrors").bind(
					"click",
					function() {
						i._datastore.store.getRegisterSales(null, null,
								"ERROR", function(q) {
									i.showErrorSaleDialogue(q)
								})
					})
		},
		setStatusSync : function() {
			if ($(".status-sync").qtip.rendered) {
				return
			}
			$(".status-online").hide();
			$(".status-offline").hide();
			$(".status-error").hide();
			$(".status-sync").show();
			i._datastore.store
					.getRegisterSales(
							null,
							null,
							"UPLOAD",
							function(r) {
								var q = r.length;
								$(".status-sync").qtip("toggle", true);
								var s = "<strong>Welcome back!</strong><br/>";
								s += "Any sales since you went offline are being uploaded, and your database is being synchronised for you.";
								$(".status-sync").qtip("option",
										"content.text", s);
								var p = setTimeout(function() {
									$(".status-sync").qtip("hide")
								}, 5000)
							})
		},
		setStatusOnline : function() {
			$(".status-alert").hide();
			$(".status-offline").hide();
			$(".status-error").hide();
			$(".status-sync").hide();
			$(".status-online").show()
		},
		setupRegisterSale : function(r, s) {
			$Register.triggerHandler("sale:new");
			this._lastProductId = undefined;
			var q = this;
			var p = function() {
				q.resizeScreenElements();
				if ("function" === typeof s) {
					s()
				}
			};
			if (r !== null) {
				q._datastore.register_sale = r;
				q._datastore.register.register_sale_id = r.id;
				a(null);
				$("#customer_search_customer_code").val("");
				q.resetRegisterSaleDisplay();
				q.resetProductEntry()
			}
			p()
		},
		completeAndNewSale : function(r) {
			debug("INFO: In Complete and new sale");
			var q = function() {
				i._displayTimer = setTimeout(
						function() {
							i
									.showStatusWindow("Saving your sale, ... just a few more seconds")
						}, 2000);
				if (i._datastore._onlineStatus != "OFFLINE"
						&& i._datastore._onlineStatus != "SYNC") {
					debug("Forcing to online");
					i._datastore.setSyncStatusOnline()
				}
				if (i._datastore._onlineStatus != "OFFLINE"
						&& i._datastore.register.ask_for_user_on_sale == 1) {
					i.showSelectUserDialogue()
				} else {
					clearTimeout(i._displayTimer);
					i.hideStatusWindow()
				}
			};
			var p = function() {
				debug("SAVE: get new sale or new");
				i._datastore.config.register_sale_id = null;
				i._datastore
						.getRegisterSaleOrNew(function(s) {
							i
									.setupRegisterSale(
											s,
											function() {
												i._datastore
														.saveConfig(
																i._datastore.config,
																function() {
																	i
																			.showStatusWindow("Saving your sale, won't be a sec");
																	var t = setTimeout(
																			q,
																			1000);
																	i._datastore
																			.uploadRegisterSales();
																	if ("function" === typeof r) {
																		r()
																	}
																})
											})
						})
			};
			i._datastore.config.register_sale_id = null;
			i._datastore.saveConfig(i._datastore.config, p)
		},
		prepareRegister : function(r, w) {
			var s = this;
			$("link#receipt_style").attr("href", r.receipt_style_sheet);
			$("#register_name .name").html(r.name);
			$("#btn-switch-register").html(r.name + "");
			s._datastore.register = r;
			s._datastore.config.register_id = r.id;
			debug("**** SAVED REGISTER " + r.id);
			var q = new Date();
			var v = q.getUTCISODate();
			var p = function() {
				debug("Bind payment buttons " + r.id);
				s.bindPaymentButtons();
				s.clearRegisterSaleProducts();
				s._datastore.updateRegisterSaleTotals(
						s._datastore.register_sale, function(x) {
							debug("Update GUI totals " + r.id);
							s.updateGUITotals(x);
							s.loadReceiptTemplate();
							s._datastore.store.getRegisterSaleProducts(x,
									function(y) {
										s.updateCurrentSaleItems(y);
										if ("function" === typeof w) {
											debug("Do callback");
											w()
										}
									});
							s.resizeScreenElements();
							s._datastore.saveConfig(s._datastore.config)
						})
			};
			var t = function() {
			};
			var u = function() {
				s._datastore.loadDataForRegister(s._datastore.register);
				s._datastore.getRegisterSaleOrNew(function(y) {
					s._datastore.register_sale = y;
					s._datastore.register.register_sale_id = y.id;
					s._datastore.config.register_sale_id = y.id;
					s.loadSale(y);
					debug("Save config on opening register");
					s._datastore.saveConfig(s._datastore.config, p)
				});
				debug("Trying to open the register");
				try {
					$.ajax({
						url : "/api/registers/" + r.id + "/open?time=" + v,
						dataType : "json",
						type : "GET",
						success : t,
						error : function(y) {
							t()
						}
					})
				} catch (x) {
					debug(x);
					t()
				}
			};
			s._datastore.saveConfig(s._datastore.config, u)
		},
		openRegister : function(r) {
			var q = this;
			var p = function() {
				if (typeof q._datastore.config.register_id == "undefined"
						|| null === q._datastore.config.register_id) {
					q._datastore.getRegisters(q._datastore.config.outlet_id,
							function(s) {
								if (s.length == 1) {
									q._datastore.setRegister(s[0], r)
								} else {
									if (s.length === 0) {
										r(null)
									} else {
										q.showSelectRegisterDialogue(s, r)
									}
								}
							})
				}
			};
			if (typeof q._datastore.config.register_id != "undefined"
					&& null !== q._datastore.config.register_id) {
				q._datastore.getRegister(q._datastore.register_id, function(s) {
					if (typeof s == "undefined") {
						debug("**** pick register");
						q._datastore.config.register_id = null;
						p()
					}
				})
			} else {
				debug("_doPickRegister");
				p()
			}
		},
		showPaymentsDialogue : function() {
			var u = this, v = this._datastore.register_sale.register_sale_products;
			u.resizeScreenElements();
			if ("undefined" === typeof v || 0 === v.length) {
				window.location.hash = "";
				alert("Oops! There are no items in the sale.");
				return false
			}
			if (u.registerWorkQueue.hasTasks()) {
				$("#please-wait").show();
				var r = window.setInterval(function() {
					if (u.registerWorkQueue.hasTasks()) {
						return
					}
					window.clearInterval(r);
					$("#please-wait").hide();
					u.showPaymentsDialogue()
				}, 100);
				return false
			}
			var q = $("#checkout-form"), t = $("#checkout_amount"), s = roundNumber(
					t.val(), 2), p = function() {
				u.hideStatusWindow();
				window.location.hash = "";
				q.hide();
				return false
			};
			$(u).unbind("key_escape").bind("key_escape", p);
			if (s !== t) {
				t.val(s)
			}
			u.hideStatusWindow();
			u.setTenderPrompt();
			q.show();
			UI.positionModalWindow(q);
			t.blur().focus().select()
		},
		showSaveSaleDialogue : function(u, z) {
			debug("show save sale dialogue", u);
			var t = this;
			var w = $(this);
			var B = z;
			var A = $("#save-sale");
			t.hideStatusWindow();
			A.find(".btn-save").unbind("click").unbind("touchstart");
			A.find(".btn-cancel").unbind("click").unbind("touchstart");
			var y = new Date(getDateFromFormat(u.sale_date,
					"yyyy-MM-dd H:mm:ss"));
			var p = new Date();
			var x = p.getUTCISODate();
			var v = $(document.createElement("div")).html(u.note).text();
			var s = $("#save-sale-note");
			s.val(v);
			s.unbind("keyup blur").bind("keyup blur", function(C) {
				var D = s.val();
				if (D.length >= 2000) {
					C.preventDefault();
					s.val(D.substr(0, 2000));
					if (C.type == "blur") {
						alert("Notes are limited to 2000 characters")
					}
					return false
				}
				return true
			});
			var q = function() {
				window.location.hash = "";
				A.hide();
				return false
			};
			w.unbind("key_escape").bind("key_escape", q);
			A.find(".btn-cancel").bind(Register.TRIGGER, q);
			A.find(".btn-save").bind(Register.TRIGGER, function() {
				var D = s.val();
				var C = A.find("#show-note").val();
				s.val("");
				A.hide();
				t.hideStatusWindow();
				$("#checkout-form").hide();
				z.call(this, D, C);
				return false
			});
			A.show();
			UI.positionModalWindow(A);
			var r = A.find("#save-sale-note").val().length;
			A.find("#save-sale-note").selectRange(r, r);
			return false
		},
		showSelectUserDialogue : function() {
			if (Is.NativeClient) {
				VendPOS.util.NativeBridge.requireUser();
				return
			}
			var q = this;
			var p = "user/switch_user?return=/sell";
			var r = $("#select-user");
			r.find(".section").load(p, function() {
				clearTimeout(q._displayTimer);
				q.hideStatusWindow();
				r.show();
				UI.positionModalWindow(r)
			})
		},
		showSelectRegisterDialogue : function(q, t) {
			if (Is.NativeClient) {
				VendPOS.util.NativeBridge.requireRegister();
				return
			}
			debug("show register dialogue");
			var r = this;
			r.showStatusWindow("Selecting register, wont be long");
			var s = $("#select-register");
			s.find("#register-list > tbody").html("");
			$(q).each(
					function(u) {
						var w = this;
						var v = $('<tr class="selectable"><td>' + w.name
								+ "</td></tr>");
						v.bind("click", function() {
							r._datastore.setRegister(w, t);
							s.hide()
						});
						s.find("#register-list > tbody:last").append(v)
					});
			var p = function() {
				s.hide();
				window.location = "/";
				return false
			};
			$(r).unbind("key_escape").bind("key_escape", p);
			s.find(".btn-cancel").bind(Register.TRIGGER, p);
			r.hideStatusWindow();
			s.show();
			UI.positionModalWindow(s)
		},
		showCloseRegisterDialogue : function() {
			var r = this;
			var q = $("#close-register");
			var s = r._datastore.config.register_id;
			var p = function() {
				window.location.hash = "";
				$("#btn-current-sale").addClass("current");
				$("#btn-close-register").removeClass("current");
				$("#btn-lookup-sale").removeClass("current");
				$("#close-register").hide();
				$("#select-sale").hide();
				$("#sell").show()
			};
			$("#btn-current-sale").removeClass("current");
			$("#btn-close-register").addClass("current");
			$("#btn-lookup-sale").removeClass("current");
			$("#sell").hide();
			$("#select-sale").hide();
			q.find(".section").html("").append("<h3>Loading</h3>");
			q.show();
			r
					.showStatusWindow("Calculating register sales totals, wont be long");
			$
					.ajax({
						url : "/register/" + s + "/close",
						success : function(t) {
							r.hideStatusWindow(null, 1, function() {
								$(r).unbind("key_escape");
								$(r).bind("key_escape", p)
							});
							q.find(".section").html("").append($(t));
							r.bindBtnClosePrint(q.find("#btn-close-print"));
							q
									.find("form")
									.submit(
											function(z) {
												var x = $(this);
												var y = confirm("This will close off this register and tally up it's sales totals.  \n\rAre you sure?");
												if (y) {
													r._datastore.register = null;
													r._datastore.register_id = null;
													r._datastore.config.register_id = null;
													r._datastore.config.register_sale_id = null;
													var w = new Date();
													var v = w.getUTCISODate();
													var u = function() {
														r
																.showStatusWindow("Saving...  Won't be a sec.");
														return true
													};
													r._datastore.config.register_id = null;
													r._datastore.config.register_sale_id = null;
													r._datastore
															.saveConfig(
																	r._datastore.config,
																	u)
												} else {
													return false
												}
											})
						}
					})
		},
		showBrowserNotSupportedDialogue : function() {
			var q = this;
			var p;
			if ($.browser.mozilla) {
				p = "Firefox"
			} else {
				if ($.browser.msie) {
					p = "Internet Explorer"
				} else {
					if ($.browser.opera) {
						p = "Opera"
					} else {
						if ($.browser.safari) {
							p = "Safari"
						} else {
							p = "Unknown"
						}
					}
				}
			}
			$("#browser-notsupported .get-gears")
					.bind(
							"click",
							function() {
								window.location.href = "http://gears.google.com/?action=install&message=You need gears to use the VendHQ POS with your browser&return="
										+ window.location.hostname + "/POS";
								return false
							});
			q.hideStatusWindow();
			$notSupportedWindow = $("#browser-notsupported");
			$notSupportedWindow.show();
			UI.positionModalWindow($notSupportedWindow)
		},
		showBrowserNotSupportedEverDialogue : function() {
			var p = this;
			p.hideStatusWindow();
			$notSupportedEverWindow = $("#browser-notsupported-ever");
			$notSupportedEverWindow.show();
			UI.positionModalWindow($notSupportedEverWindow)
		},
		showSelectSaleDialogue : function(t, u) {
			debug("show sale dialogue");
			var s = this;
			var p = u;
			var r = function() {
				$("#close-register").hide();
				$("#select-sale").hide();
				$("#sell").show();
				$("#btn-current-sale").addClass("current");
				$("#btn-close-register").removeClass("current");
				$("#btn-lookup-sale").removeClass("current");
				window.location.hash = "";
				return false
			};
			$(s).unbind("key_escape");
			$(s).bind("key_escape", r);
			var q = $("#select-sale");
			q.find("#register-sale-list > tbody").html("");
			if ($(t).length === 0) {
				q
						.find("#register-sale-list > tbody")
						.html(
								'<tr><td colspan="99"><h3>No saved sales</h3></td></tr>')
			}
			$(t)
					.each(
							function(w) {
								var A = this;
								var x = "";
								var v = "";
								if (A.customer
										&& typeof A.customer !== "undefined") {
									x = A.customer.customer_code;
									if (A.customer
											&& typeof A.customer !== "undefined"
											&& typeof A.customer.name !== "undefined"
											&& A.customer.name.length > 1) {
										v += A.customer.name
									} else {
										if (A.customer
												&& typeof A.customer !== "undefined"
												&& typeof A.customer.company_name !== "undefined"
												&& A.customer.company_name.length > 1) {
											v += A.customer.company_name
										}
									}
								}
								var z = new Date(getDateFromFormat(A.sale_date,
										"yyyy-MM-dd HH:mm:ss"))
										.getLocalHumanDate();
								var y = $('<tr class="selectable"><td>'
										+ z
										+ "</td><td>"
										+ A.status
										+ "</td><td>"
										+ A.user_name
										+ "</td><td>"
										+ v
										+ "</td><td>"
										+ x
										+ "</td><td>"
										+ roundNumber(parseFloat(A.total_price)
												+ parseFloat(A.total_tax), 2)
										+ "</td><td>"
										+ A.note.substr(0, 2000)
										+ '</td><td><div class="controls"><ul><li><a href="#sale/'
										+ A.id
										+ '">Open</a></li></ul></div></td></tr>');
								y
										.bind(
												"click",
												function() {
													var B = A;
													s._datastore.config.register_sale_id = A.id;
													q.hide();
													$("#btn-current-sale")
															.addClass("current");
													$("#btn-close-register")
															.removeClass(
																	"current");
													$("#btn-lookup-sale")
															.removeClass(
																	"current");
													if ("function" === typeof p) {
														p(A)
													}
													return false
												});
								q.find("#register-sale-list > tbody:last")
										.append(y)
							})
		},
		showErrorSaleDialogue : function(r) {
			debug("show error dialogue");
			var q = this;
			var s = $("#error-sale");
			$(".status-offline").qtip("option", "content.text", "new content");
			s.find("#errors").html("");
			$(r).each(function(t) {
				var u = JSON.stringify(this);
				s.find("#errors").append(u).append("<hr/>")
			});
			var p = function() {
				window.location.hash = "";
				s.hide();
				return false
			};
			$(q).unbind("key_escape");
			$(q).bind("key_escape", p);
			q.retryFunction = function() {
				var t = function() {
					s.hide();
					q._datastore.uploadRegisterSales()
				};
				q._datastore.store.retryRegisterSaleErrors(t);
				return false
			};
			q.clearFunction = function() {
				var t = function() {
					s.hide();
					q._datastore.triggerStatusOnline();
					q.setStatusOnline()
				};
				q._datastore.store.clearRegisterSaleErrors(t);
				return false
			};
			s.find(".btn-cancel").bind(Register.TRIGGER, p);
			s.find(".btn-retry").bind(Register.TRIGGER, q.retryFunction);
			s.find(".btn-clear").bind(Register.TRIGGER, q.clearFunction);
			s.show()
		},
		showStatusWindow : function(F, s, D, z, w) {
			debug("GUI: show status dialogue");
			$("#loading-window").hide();
			var x = this, y = $(this), u = $(document.body), C = $("#status-window"), p = C
					.find(".btn-cancel"), B = C.find(".btn-ok"), E = {
				timeout : 0,
				callback_on_timeout : function() {
				},
				title : "Status",
				okText : "OK",
				cancelText : "Cancel"
			};
			for ( var A = arguments, v = 3, t = A.length; v < t; v++) {
				switch (typeof A[v]) {
				case "number":
					E.timeout = A[v];
					break;
				case "string":
					E.timeout = parseInt(A[v], 10);
					break;
				case "function":
					E.callback_on_timeout = A[v];
					break;
				case "object":
					E = $.extend({}, E, A[v]);
					break
				}
			}
			C.clearQueue();
			if (typeof s === "function") {
				var q = function() {
					if (!C.is(":visible")) {
						return false
					}
					C.hide();
					s();
					u.unbind("key_enter");
					return false
				};
				u.unbind("key_enter").bind("key_enter", q).blur();
				B.text(E.okText).unbind(Register.TRIGGER).bind(
						Register.TRIGGER, q).show()
			} else {
				B.unbind(Register.TRIGGER).hide()
			}
			if (typeof D === "function") {
				var r = function() {
					if (!C.is(":visible")) {
						return false
					}
					C.hide();
					D();
					return false
				};
				y.unbind("key_escape").bind("key_escape", r);
				p.text(E.cancelText).unbind(Register.TRIGGER).bind(
						Register.TRIGGER, r).show()
			} else {
				p.unbind(Register.TRIGGER).hide()
			}
			debug("STATUS: Updated status message to " + F);
			C.find("h2").text(E.title);
			C.find(".status-message").html(F);
			C.hide();
			if (E.timeout) {
				C.show().delay(E.timeout).fadeOut("fast", function() {
					B.unbind("blur");
					if (typeof E.callback_on_timeout === "function") {
						E.callback_on_timeout.call(this)
					}
				})
			} else {
				C.show()
			}
			UI.positionModalWindow(C);
			B.bind("blur", function() {
				B.focus()
			});
			$("#product_search_product_sku").blur();
			B.focus();
			return false
		},
		hideStatusWindow : function(r, s, t) {
			var q = this;
			var p = $("#status-window").find(".btn-ok");
			p.unbind("blur");
			m();
			$("#status-window").clearQueue();
			if (typeof r !== "undefined" && r !== null) {
				q.showStatusWindow(r)
			}
			if ("function" !== typeof t) {
				t = function() {
					return
				}
			}
			s = parseInt(s, 10);
			if (s) {
				$("#status-window").delay(s).fadeOut("fast", t)
			} else {
				$("#status-window").hide(null, t)
			}
		},
		registerDeleteSaleItem : function(q) {
			q = (q instanceof jQuery) ? q : $(q);
			var p = this, r = this._datastore, s = r.register_sale;
			q
					.bind(
							"click",
							function(t) {
								t.preventDefault();
								debug("Deleting item from sale in DB for register sale "
										+ s.id);
								r.store.deleteRegisterSaleProduct(s, q
										.attr("data-id"), function() {
									r.updateRegisterSaleTotals(s,
											p.updateGUITotals)
								});
								d(q.attr("data-id"));
								m();
								return false
							})
		},
		bindHashChange : function() {
			var p = this;
			if (typeof $(window).hashchange === "function") {
				$(window)
						.hashchange(
								function() {
									var s = window.location.hash.substring(1)
											.split("/");
									var t, q;
									switch (s[0]) {
									case "reset":
										window.localStorage
												.removeItem("config");
										window.localStorage
												.removeItem("registers");
										window.localStorage.removeItem("taxs");
										window.localStorage
												.removeItem("payment_types");
										p._datastore.store.initLocalDataStore(
												function() {
													debug("RESET LOCAL STORE");
													window.location = "/sell"
												}, true);
										break;
									case "pay":
										t = s[1];
										p.showPaymentsDialogue();
										break;
									case "sale":
										t = s[1];
										p
												.showStatusWindow(
														"Loading sale.  Won't be a sec.",
														null,
														function() {
															window.location = "/"
														}, 2000);
										p._datastore.register_sale.id = t;
										p._datastore.register_sale_id = t;
										p._datastore.config.register_sale_id = t;
										p._datastore
												.getRegisterSaleFromServer(
														t,
														function(u) {
															p._datastore.store
																	.saveNewRegisterSale(
																			u,
																			p.loadSale)
														});
										$(p).unbind("key_escape");
										window.location.hash = "";
										break;
									case "product":
										t = s[1];
										$
												.ajax({
													url : "/product/" + t,
													success : function(u) {
														$(
																"#view-object .container")
																.html($(u));
														UI
																.positionModalWindow($("#view-object"))
													}
												});
										q = $("#view-object").find(
												".btn-back-to-sale");
										q.bind("click", function() {
											window.location.hash = "";
											$("#view-object").fadeOut("fast")
										});
										$("#view-object").show();
										$("#view-object .container")
												.html(
														'<div class="section"><h3>Loading</h3></div>');
										UI
												.positionModalWindow($("#view-object"));
										break;
									case "customer":
										t = s[1];
										debug("test --- --- --- --- loading customer");
										$
												.ajax({
													url : "/customer/" + t,
													success : function(u) {
														$(
																"#view-object .container")
																.html($(u));
														UI
																.positionModalWindow($("#view-object"))
													}
												});
										q = $("#view-object").find(
												".btn-back-to-sale");
										q.bind("click", function() {
											window.location.hash = "";
											$("#view-object").fadeOut("fast")
										});
										$("#view-object").show();
										$("#view-object .container")
												.html(
														'<div class="section"><h3>Loading</h3></div>');
										UI
												.positionModalWindow($("#view-object"));
										break;
									case "customer-add":
										t = s[1];
										p
												.showStatusWindow(
														"Loading customer.  Won't be a sec.",
														null,
														function() {
															window.location = "/"
														}, 2000);
										var r = $(this);
										p._datastore
												.getCustomer(
														t,
														function(u) {
															p._datastore.register_sale.customer_id = u.id;
															p
																	.setCustomerDetails(u);
															p._datastore
																	.setRegisterSaleCustomer(
																			Datastore.register_sale,
																			u,
																			u.customer_group_id,
																			null,
																			function() {
																			})
														});
										$(p).unbind("key_escape");
										window.location.hash = "";
										break
									}
								})
			}
		},
		bindKeyListeners : function() {
			var p = this;
			p.keyhandler = {};
			$(document.body).keydown(
					function(q) {
						debug("Key pressed: " + q.keyCode, q);
						switch (q.keyCode) {
						case 17:
						case 91:
							return false;
						case 18:
							return false;
						case 27:
							$(p).trigger("key_escape");
							break;
						case 13:
							$(document.body).trigger("key_enter");
							break;
						case 69:
							if (q.ctrlKey === true || q.metaKey === true) {
								p.print("**** Open Cash Drawer ****");
								return false
							}
							return 69;
						case 86:
							if ((q.ctrlKey === true || q.metaKey === true)
									&& q.shiftKey === true) {
								alert($("#pos-version").html());
								return false
							}
							return 69;
						case 117:
							p.openSavedSale();
							return false;
						case 118:
							p.closeRegister();
							return false;
						case 116:
							if (q.altKey) {
								p.saveSaleNote()
							} else {
								p.parkSale()
							}
							return false;
						case 115:
							if (q.altKey) {
								p.parkSale()
							} else {
								p.showPaymentsDialogue()
							}
							return false
						}
					})
		},
		bindBtnDiscount : function(t) {
			var r = this, p = false, u = r._datastore.config.cashier_discount, q = r._datastore.config.account_type, s = $(t);
			if ("cashier" != q.toLowerCase()) {
				p = true
			} else {
				if (("undefined" === typeof u || u)) {
					p = true
				}
			}
			if (p) {
				s.bind(
						"click",
						function() {
							var v = prompt("Enter percentage", 0);
							if (v) {
								$("#checkout_amount").val(
										$("#checkout_amount").val() / 100 * v)
							}
							return false
						}).removeClass("disabled")
			} else {
				s.bind("click", function() {
					return false
				}).attr("disabled", "disabled").addClass("disabled")
			}
		},
		bindBtnClosePrint : function(q) {
			var p = this;
			$(q).bind("click", function() {
				p.print($("#close-register .printable").html());
				return false
			})
		},
		bindBtnDiscountSale : function(t) {
			var r = this, p = false, u = r._datastore.config.cashier_discount, q = r._datastore.config.account_type, s = $(t);
			if ("cashier" != q.toLowerCase()) {
				p = true
			} else {
				if (("undefined" === typeof u || u)) {
					p = true
				}
			}
			if (p) {
				s.bind(
						"click",
						function() {
							var v = prompt(
									"Enter total sale discount percentage", 0);
							if (v) {
								m();
								r._datastore.applyDiscountToSale(
										r._datastore.register_sale,
										(1 - (v / 100)),
										r.refreshCustomerPricingDisplay)
							}
							return false
						}).removeClass("disabled")
			} else {
				s.bind("click", function() {
					return false
				}).attr("disabled", "disabled").addClass("disabled")
			}
		},
		bindChangeItemPriceClick : function(q) {
			var p = this;
			$(q)
					.bind(
							"click",
							function() {
								p._datastore.store
										.getRegisterSaleProduct(
												$(this).attr("data-id"),
												function(s) {
													if (s) {
														var r = s.price;
														if (s.display_retail_price_tax_inclusive) {
															r = s.price + s.tax
														}
														var u = prompt(
																"Enter the new price",
																r);
														if (u) {
															var t = r
																	- parseFloat(u);
															s.price = u;
															s.discount = t;
															s.price_set = 1;
															if (s.display_retail_price_tax_inclusive) {
																s.tax = s.price
																		- (s.price / (1 + parseFloat(s.tax_rate)));
																s.price = s.price
																		- s.tax
															} else {
																s.tax = s.price
																		* s.tax_rate
															}
															p._datastore.store
																	.saveRegisterSaleProduct(
																			s,
																			function() {
																				p
																						.updateSalesLine(s);
																				p._datastore.register_sale.register_sale_products
																						.push(s);
																				p._datastore
																						.updateRegisterSaleTotals(
																								p._datastore.register_sale,
																								function(
																										v) {
																									p
																											.updateGUITotals(v);
																									m()
																								})
																			})
														}
													}
												});
								return false
							})
		},
		bindChangeItemQuantClick : function(q) {
			var p = this;
			$(q)
					.bind(
							"click",
							function() {
								debug("setting quant for item in sale in DB for register sale "
										+ p._datastore.register_sale.id);
								debug("register sale product id = "
										+ $(this).attr("data-id"));
								var r = p._datastore.register_sale;
								p._datastore.store
										.getRegisterSaleProduct(
												$(this).attr("data-id"),
												function(t) {
													if (t) {
														var x = function() {
															p
																	.updateSalesLine(t);
															p._datastore.register_sale.register_sale_products
																	.push(t);
															p._datastore
																	.updateRegisterSaleTotals(
																			p._datastore.register_sale,
																			function(
																					y) {
																				p
																						.updateGUITotals(y)
																			});
															m()
														};
														var v = function(y) {
															if (!t.price_set
																	&& y) {
																if (t.quantity >= y.min_units) {
																	t.price = parseFloat(roundNumber(
																			y.price,
																			2));
																	t.tax = parseFloat(roundNumber(
																			y.tax,
																			2));
																	t.tax_id = y.tax_id
																}
															}
															p._datastore.store
																	.saveRegisterSaleProduct(
																			t,
																			x)
														};
														var w = t.quantity;
														var s = prompt(
																"Enter the new quantity",
																w);
														if (s) {
															t.quantity = s;
															var u = p._datastore
																	.getRegisterSaleCustomer(r);
															p._datastore.store
																	.getPriceBookEntryForRegisterSale(
																			t,
																			u,
																			p._datastore.config.default_customer_group_id,
																			p._datastore.config.outlet_id,
																			v)
														}
													}
												});
								return false
							})
		},
		updateSalesLine : function(u) {
			var p = this, C = p._datastore, z = $(
					"#register-display #table_sale_items tbody").eq(0), y = u.id, J = u.name, B = u.status, w = parseFloat(u.quantity), F = parseFloat(u.price), q = parseFloat(u.discount), A = parseFloat(u.tax), r = u.display_retail_price_tax_inclusive, G = clone(C.culture_info), v = false, D = C.config.cashier_discount, s = C.config.account_type, x, I, t, E, H;
			if ("cashier" != s.toLowerCase()) {
				v = true
			} else {
				if (("undefined" === typeof D || D)) {
					v = true
				}
			}
			G.symbol = "";
			$("tr", z).each(function() {
				var K = $(this);
				if (y === K.attr("data-id")) {
					K.remove()
				} else {
					K.removeClass("current")
				}
			});
			if (r) {
				I = roundNumber(w * (F + A), 2);
				x = roundNumber(F + A, 2).toString()
			} else {
				I = roundNumber(w * F, 2);
				x = roundNumber(F, 2).toString()
			}
			if (B != "CONFIRMED") {
				t = '<td class="quantity"><a href="#set-quant" class="set-quant" data-id="'
						+ y + '">' + w + " </a></td>";
				if (v) {
					E = '<td class="price"><a href="#set-price" class="set-price " data-id="'
							+ y + '">@&nbsp;' + x + "</a></td>"
				} else {
					E = '<td class="price"><span class="fixed-price">@&nbsp;'
							+ x + "</span></td>"
				}
				H = '<td class="action"><a href="/api/register_sales/'
						+ u.register_sale_id
						+ "/product/"
						+ y
						+ '/delete" data-id="'
						+ y
						+ '" class="delete tip" title="Remove this line from the sale"><img class="button-icon" src="/images/button-icon-remove-new.png"></a></td>'
			} else {
				t = '<td class="quantity"><span class="fixed-quantity">' + w
						+ "</span></td>";
				E = '<td class="price"><span class="fixed-price">@&nbsp;' + x
						+ "</span></td>";
				H = '<td class="action"></td>'
			}
			z
					.prepend([
							'<tr data-id="'
									+ y
									+ '" class="current" style="background-image: url(/images/hash-white.png);">',
							t,
							'<td class="name">' + J + "</td>",
							E,
							'<td class="price-total">'
									+ ($(
											"<span>" + roundNumber(I, 2)
													+ "</span>")
											.formatCurrency(G).html())
									+ "</td>", H, "</tr>" ].join());
			p.registerDeleteSaleItem($("a.delete[data-id=" + y + "]", z));
			p.bindChangeItemPriceClick($("a.set-price[data-id=" + y + "]", z));
			p.bindChangeItemQuantClick($("a.set-quant[data-id=" + y + "]", z))
		},
		updateCurrentSaleItems : function(r) {
			var p = this, q = this._datastore;
			$(r)
					.each(
							function() {
								var s = this, t = s.id;
								if (s.product_id) {
									q.store
											.getProductById(
													s.product_id,
													p.retailer_id,
													function(u) {
														if (u
																&& typeof u != "undefined") {
															q.store
																	.getRegisterSaleProduct(
																			t,
																			function(
																					v) {
																				p
																						.updateSalesLine(v)
																			})
														} else {
															p
																	.updateSalesLine({
																		id : t,
																		quantity : 0,
																		price : 0,
																		name : "Unknown/Deleted product"
																	})
														}
													},
													function() {
														debug("REGISTER: Opened saved sale")
													})
								}
							})
		},
		refreshCustomerPricingDisplay : function(p) {
			i._datastore.store.getRegisterSaleProducts(p, function(q) {
				i.updateCurrentSaleItems(q)
			});
			i.updateGUITotals(p);
			i.loadReceiptTemplate()
		},
		updateUserAndOutletname : function() {
			var p = this;
			var q = '<img src="/images/down_arrow.png">';
			$("#user_name").html(
					'<span title="' + p._datastore.config.user_display_name
							+ '">' + p._datastore.config.user_display_name
							+ "</span>" + q);
			$("#outlet_name")
					.html(
							'<span title="'
									+ (p._datastore.config.outlet_name ? p._datastore.config.outlet_name
											: "All outlets")
									+ '">'
									+ (p._datastore.config.outlet_name ? p._datastore.config.outlet_name
											: "All outlets") + "</span>" + q)
		},
		updateGUITotals : function(z, C) {
			var r = {};
			if (typeof z.totals == "undefined") {
				r = {
					total_price : 0,
					total_tax : 0,
					total_to_pay : 0
				}
			} else {
				r = z.totals
			}
			var u = z.register_sale_payments;
			var B;
			var q = r.total_price;
			if (isNaN(q)) {
				q = 0
			}
			var E = r.total_tax;
			if (isNaN(E)) {
				E = 0
			}
			var F = r.total_to_pay;
			if (isNaN(F)) {
				F = 0
			}
			$("#register-checkout-form #sale-subtotal").html(
					roundNumber(parseFloat(q), 2)).formatCurrency(
					i._datastore.culture_info);
			$("#register-checkout-form #sale-totals-total").html(
					roundNumber(parseFloat(q) + parseFloat(E), 2))
					.formatCurrency(i._datastore.culture_info);
			$("#register-checkout-form #sale-totals-taxes").html(
					roundNumber(parseFloat(E), 2)).formatCurrency(
					i._datastore.culture_info);
			$(
					"#register-checkout-form #table-payment-totals .payment-totals-topay")
					.each(
							function() {
								$(this).html(roundNumber(parseFloat(F), 2))
										.formatCurrency(
												i._datastore.culture_info)
							});
			if (z.tax_name) {
				var A = z.tax_name;
				if ("undefined" !== A && "undefined" !== typeof A && "" !== A
						&& null !== A) {
					$("#table-sale-totals #tax-name").html(" (" + A + ")")
				}
			}
			var t = $("#checkout_amount"), x = roundNumber(F, 2);
			if (t.val() !== x) {
				t.val(x).select()
			}
			$("div#register #table-payment-totals tbody tr.payment").remove();
			if (typeof u === "undefined") {
				return
			}
			for ( var w = 0; w < u.length; w++) {
				var p = u[w];
				B = "";
				var y = p.name;
				if (p.amount < 0) {
					if (p.payment_type_id == 1) {
						B = "";
						y = "Change"
					} else {
						B = "Refund "
					}
				}
				var D = $('<tr class="payment" data-id="' + p.payment_type_id
						+ '"></tr>');
				D.append($('<td class="payment-type">' + y + "</td>"));
				var v = $('<td class="total-amount">' + B + "</td>");
				v.append($("<span> " + roundNumber(p.amount, 2) + "</span>")
						.formatCurrency(i._datastore.culture_info));
				D.append(v);
				var s = $("div#register #table-payment-totals tbody tr#topay")
						.before(D)
			}
			i.determinePaymentColours($("#checkout_amount"));
			i.resizeScreenElements();
			i.setTenderPrompt();
			if ("function" === typeof C) {
				C.call()
			}
		},
		deleteSaleItem : function(r, p) {
			var q = this;
			if (p === 510) {
				c({
					name : r.error,
					image : "",
					description : r.details
				})
			} else {
				$(r.line_items).each(function() {
					d(this.id)
				});
				q.updateGUITotals(r.register_sale);
				q.loadReceiptTemplate()
			}
		},
		resetRegisterSaleDisplay : function() {
			var p = this;
			var q = {
				line_items : [],
				register_sale : {
					tax_name : "",
					tax_rate : 0,
					totals : {
						total_price : 0,
						total_tax : 0,
						total_to_pay : 0
					},
					register_sale_payments : []
				}
			};
			p.clearRegisterSaleProducts();
			p.updateCurrentSaleItems(q.line_items);
			p.updateGUITotals(q.register_sale);
			$("#customer-search-form .customer-editable").show();
			$("#btn-close").hide();
			$("#btn-void").show();
			$("#product_search_quantity").val(1);
			$("#product_search_product_sku").val("");
			m()
		},
		clearRegisterSaleProducts : function() {
			$("body#register div#register #table_sale_items tbody").empty()
		},
		clearRegisterSalePayments : function() {
			$("body#register div#register #table-payment-totals tbody").find(
					"tr.payment").remove()
		},
		setKeypad : function(p) {
			this._keypad = p
		},
		registerFieldFocus : function(p) {
			this._keypad.setFocusField(p)
		},
		addKeypadListenerToFields : function() {
			var p = this;
			var q = $("#register-input");
			q.find("input.use-keypad").each(function(t) {
				var s = this;
				$(s).bind("focus", function() {
					p.registerFieldFocus($(this).attr("id"))
				})
			});
			var r = $("#register-checkout-form");
			r.find("input.use-keypad").each(function(t) {
				var s = this;
				$(s).bind("focus", function() {
					p.registerFieldFocus($(this).attr("id"))
				})
			})
		}
	}
})();
Register.keypad = (function() {
	var a;
	return {
		init : function(d, c) {
			var b = this;
			$("#" + d + " .key").each(
					function(e) {
						$(this)
								.bind(
										"click",
										function() {
											if (typeof c !== "undefined") {
												b._focusField = c
											}
											var g = this;
											$("#" + b._focusField).focus();
											if ($(g).hasClass("move-next")) {
												$("#" + b._focusField)
														.moveNext()
											}
											if ($(g).hasClass("move-prev")) {
												$("#" + b._focusField)
														.movePrev()
											}
											if ($(g).hasClass("clear")) {
												$("#" + b._focusField).val("")
											}
											if ($(g).hasClass("replace")) {
												$("#" + b._focusField).val(
														$(this).val())
											} else {
												var f = $("#" + b._focusField)
														.replaceSelection(
																$(this).val())
											}
											if ($(g).hasClass("with-return")) {
												$("#" + b._focusField).parents(
														"form:first").submit()
											}
										})
					})
		},
		getCurrent : function() {
			return this
		},
		setFocusField : function(b) {
			this._focusField = b
		}
	}
})();
var $Datastore = $(Datastore), $Register = $(Register);
var UI = {};
UI.overrideEnterToTabs = function() {
	$(".enter-to-tab").keydown(function(a) {
		if (a.keyCode === 13) {
			$(this).moveNext();
			return false
		}
	})
};
UI.messageHider = function() {
	$(".success-message .close a").each(function() {
		$(this).bind("click", function() {
			$(this).parent().parent().fadeOut("fast");
			return false
		})
	})
};
UI.clickToSelect = function() {
	$(".click-to-select").each(function(a) {
		if (!Is.iOS) {
			$(this).bind("click", function() {
				$(this).select()
			})
		} else {
			$(this).bind("touchstart", function() {
				$(this).select()
			})
		}
	})
};
UI.bindDelete = function(d, e, b) {
	var a = (d instanceof jQuery) ? d : $(d), c;
	d = (d instanceof jQuery) ? d[0] : d;
	a.find("a.delete").bind(
			"click",
			function(g) {
				var h = true, i = $(this), f = $();
				if (a.hasClass("group-parent")) {
					f = $('.group-child[data-group="' + a.attr("data-group")
							+ '"]', a.parent())
				}
				if (typeof b != "undefined") {
					h = confirm(b)
				}
				if (h) {
					a.addClass("deleted").fadeOut("fast");
					if (f.length) {
						f.addClass("deleted").fadeOut("fast")
					}
					$.ajax({
						url : i.attr("href"),
						type : "DELETE",
						success : function(k, j) {
							a.remove();
							if (f.length) {
								f.remove()
							}
							if ("function" === typeof e) {
								e(d)
							}
						},
						error : function(k, j) {
							a.fadeIn("fast");
							if (f.length) {
								f.fadeIn("fast")
							}
							if ("function" === typeof e) {
								e(d)
							}
						}
					})
				}
				g.preventDefault();
				return false
			})
};
UI.bindPleaseWait = function() {
	$(".please-wait").live("click", function() {
		$("#please-wait").show();
		return true
	})
};
UI.bindAreYouSure = function() {
	$(".are-you-sure").bind("click", function(b) {
		var a = $(this).attr("title");
		if (typeof a == "undefined" || a === "") {
			a = "Are you sure"
		}
		var c = confirm(a);
		if (c) {
			return true
		} else {
			b.stopPropagation();
			return false
		}
	})
};
UI.bindAlerts = function() {
	$(".alert").qtip({
		content : {
			attr : "title"
		},
		show : {
			solo : true,
			event : "mouseover click mouseenter"
		},
		hide : {
			delay : 300,
			fixed : true
		},
		position : {
			my : "bottom left",
			at : "top right"
		},
		style : {
			classes : "ui-tooltip-green",
			tip : {
				corner : "bottom left",
				mimic : "bottom left",
				border : 4,
				offset : 20,
				width : 20,
				height : 10
			}
		}
	})
};
UI.bindTip = function(b, c, a) {
	if (typeof c == "undefined") {
		c = "bottom left"
	}
	if (typeof a == "undefined") {
		a = "top right"
	}
	b.qtip({
		content : {
			attr : "title"
		},
		show : {
			delay : 1000,
			solo : true,
			event : "mouseenter"
		},
		hide : {
			delay : 2000,
			fixed : true
		},
		position : {
			my : c,
			at : a,
			adjust : {
				x : -5,
				y : 0
			}
		},
		style : {
			classes : "ui-tooltip-green",
			tip : {
				corner : c,
				border : 4,
				offset : 20,
				width : 20,
				height : 10
			}
		}
	})
};
UI.bindTips = function() {
	$(".tip").each(function(a) {
		UI.bindTip($(this))
	});
	$(".tip-left").each(function(a) {
		UI.bindTip($(this), "bottom right", "top right")
	})
};
UI.bindTipImmediate = function(d, e, a, f, c) {
	if (typeof e == "undefined") {
		e = "bottom center"
	}
	if (typeof a == "undefined") {
		a = "top center"
	}
	if (typeof f == "undefined") {
		f = 0
	}
	var b = {
		content : {
			attr : "title"
		},
		show : {
			delay : 0,
			solo : true,
			event : "mouseenter click"
		},
		hide : {
			delay : 2000,
			fixed : true
		},
		position : {
			my : e,
			at : a,
			adjust : {
				x : 0
			}
		},
		style : {
			classes : "ui-tooltip-green",
			tip : {
				corner : e,
				mimic : e,
				border : 4,
				offset : f,
				width : 20,
				height : 10
			}
		}
	};
	if (typeof c != "undefined") {
		b.content = {
			text : "<h3>Loading...</h3>",
			ajax : {
				url : c,
				once : false
			}
		}
	}
	d.qtip(b)
};
UI.bindTipsImmediate = function() {
	$(".tipImmediate").each(function(a) {
		UI.bindTipImmediate($(this))
	})
};
UI.bindStatusTips = function() {
	$(".status").qtip({
		content : {
			attr : "title"
		},
		show : {
			delay : 500,
			solo : true,
			event : "mouseenter"
		},
		hide : {
			delay : 500,
			fixed : true
		},
		position : {
			my : "bottom center",
			at : "top left",
			adjust : {
				x : 15,
				y : 3
			}
		},
		style : {
			classes : "ui-tooltip-green",
			tip : {
				corner : "bottom center",
				mimic : "bottom center",
				border : 4,
				offset : 0,
				width : 20,
				height : 10
			}
		}
	})
};
UI.bindSignin = function() {
	UI.bindUserInfo($("#user_name"))
};
UI.bindUserInfo = function(a, b, c) {
	if (typeof b == "undefined") {
		b = {
			my : "top left",
			at : "bottom left",
			adjust : {}
		}
	}
	if (typeof c == "undefined") {
		c = 0
	}
	a.qtip({
		content : {
			text : "<h3>Loading...</h3>",
			ajax : {
				url : a.attr("href"),
				once : false,
				error : function() {
					this.hide()
				}
			}
		},
		show : {
			delay : 0,
			solo : true,
			event : "click"
		},
		hide : {
			delay : 500,
			fixed : true,
			event : "click mouseout"
		},
		position : b,
		style : {
			classes : "ui-tooltip-light user_info",
			tip : {
				corner : b.my,
				mimic : b.my,
				border : 2,
				offset : c,
				width : 0,
				height : 0
			}
		}
	}).click(function(d) {
		d.preventDefault()
	})
};
UI.bindUserInfoTips = function() {
	$(".user-info").each(function(a) {
		UI.bindUserInfo($(this), {
			my : "bottom left",
			at : "top right"
		}, 10)
	})
};
UI.bindUserInfoPopupTips = function() {
	$(".user-info-popup").each(
			function(a) {
				$(this).bind("click", function() {
					return false
				});
				UI.bindTipImmediate($(this), "bottom left", "top right", 10, $(
						this).attr("href"))
			})
};
UI.bindOutletChange = function() {
	$("a#outlet_name").qtip({
		content : {
			text : "<h3>Loading...</h3>",
			ajax : {
				url : $("#outlet_name").attr("href"),
				once : false
			}
		},
		show : {
			delay : 0,
			solo : true,
			event : "click"
		},
		hide : {
			delay : 500,
			fixed : true
		},
		position : {
			my : "top left",
			at : "bottom left",
			adjust : {}
		},
		style : {
			classes : "ui-tooltip-light user_info",
			tip : {
				width : 0,
				height : 0
			}
		}
	});
	$("#outlet_name").click(function() {
		return false
	})
};
UI.bindPostXero = function() {
	$(".xero-send")
			.bind(
					"click",
					function() {
						var b = $(this).parent();
						var a = this.href;
						b
								.html("<a href='#posting' ><img class='indicator' src='/images/ajax-loader.gif'/>Posting</a>");
						$.ajax({
							url : a,
							data : {},
							success : function(c, e, d) {
								if (d.status == 200) {
									b.html(c);
									return false
								} else {
									window.location = a
								}
							},
							error : function(d, e, c) {
								if (d.status === 0) {
									window.location = a
								} else {
									b.html("<a href='" + a
											+ "'>Problem posting</a>");
									$("#dialog:ui-dialog").dialog("destroy");
									$("#dialog-message").html(d.responseText);
									$("#dialog-message").dialog({
										modal : true,
										width : 500,
										buttons : {
											Ok : function() {
												$(this).dialog("close")
											}
										}
									})
								}
							}
						});
						return false
					})
};
UI.bindDropdownTabs = function() {
	var a = null;
	$(".dropdown a.expand").bind("click", function() {
		var d = $(this);
		if (!d.parent().hasClass("active")) {
			if (a !== null) {
				c(a)
			}
			b(d)
		} else {
			c(d)
		}
		return false
	});
	$(document.body)
			.bind(
					"mousedown",
					function(k) {
						if (a !== null) {
							var m = a.parent();
							var f = m.find(".expander");
							var n = m.offset().left;
							var l = (m.offset().left + m.outerWidth());
							var g = m.offset().top;
							var i = (m.offset().top + m.outerHeight());
							var o = f.offset().left;
							var h = (f.offset().left + f.outerWidth());
							var j = f.offset().top;
							var d = (f.offset().top + f.outerHeight());
							if (!((k.pageX > n && k.pageX < l && k.pageY > g && k.pageY < i) || (k.pageX > o
									&& k.pageX < h && k.pageY > j && k.pageY < d))) {
								c(a)
							}
						}
					});
	function c(d) {
		$(d).parent().removeClass("active");
		a = null
	}
	function b(d) {
		$(d).parent().addClass("active");
		a = d
	}
};
UI.bindDashboardPreferences = function() {
	$("#dashboard a.img-star").live("click", function() {
		var c = $(this);
		var b = c.closest("ul");
		c.addClass("selected");
		a(b);
		$.ajax({
			url : $(this).attr("href"),
			dataType : "json",
			success : function(d) {
				if (!d.starred) {
					c.removeClass("selected");
					a(b)
				}
			}
		});
		return false
	});
	var a = function(c) {
		var d = [];
		var b = [];
		c.find("li").each(function() {
			var e = $(this).find("a.img-star");
			if (e.hasClass("selected")) {
				d.push($(this))
			} else {
				b.push($(this))
			}
			$(this).remove()
		});
		$.each(d, function(e, f) {
			c.append(f)
		});
		$.each(b, function(e, f) {
			c.append(f)
		})
	}
};
UI.initPriceBookEdit = function(d, e) {
	var c = function(f) {
		return [ '<span class="name">' + f.name + "</span>",
				'<div class="details">',
				'<span class="handle">Handle: ' + f.handle + "</span> / ",
				'<span class="sku">SKU: ' + f.sku + "</span>", "</div>" ]
				.join("")
	};
	var a = function(h) {
		var g = h;
		var f = function() {
			var i = g;
			d += "&product_id=" + i.id + "&product_sku=" + i.sku;
			$.ajax({
				url : d,
				success : function(l) {
					var k = $("#price_book-items table tbody");
					var j = $("<tr></tr>", {
						html : l,
						"class" : "price_book-product form-row"
					}).appendTo(k);
					e(j)
				}
			});
			$("#product_search_product_id").val("").removeData("key");
			$("input#product_search").val("");
			return ""
		};
		return f
	};
	var b = {
		minChars : 2,
		width : 320,
		max : 100,
		scroll : true,
		scrollHeight : 300,
		cacheLength : 0,
		matchContains : "word",
		autoFill : false,
		delay : 200,
		dataType : "json",
		formatItem : c,
		formatResult : a,
		parse : function(h) {
			var f = [];
			var j = h.products;
			for ( var g = 0; g < j.length; g++) {
				j[g].type = "product";
				f[f.length] = {
					data : j[g],
					value : j[g].name,
					result : b.formatResult && b.formatResult(j[g])
				}
			}
			return f
		}
	};
	$("input#product_search").autocomplete(
			"/product/productSearch?format=json&parent=1", b);
	$("input#product_search").live("keypress", function(f) {
		if (f.keyCode === 13) {
			return false
		}
	});
	$("input#product_search").live("keydown", function(g) {
		if (g.keyCode === 13) {
			var f = $("#product_search_product_id").data("key");
			if (f !== undefined) {
				$("#product_search_product_id").val("").removeData("key");
				$(this).val("")
			}
			return false
		}
	});
	$("input#product_search").live("keyup", function(f) {
		if (f.keyCode === 13) {
			return false
		}
	})
};
UI.bindUserList = function(b) {
	b = b.replace("_", "-");
	var d = $.formatCurrency.regions[b];
	if (d === undefined) {
		d = $.formatCurrency.regions["en-US"]
	}
	$(".input-text").each(e);
	$(".input-text")
			.live("change", e)
			.live("keyup", e)
			.keypress(
					function(g) {
						if (g.which === 13) {
							a($(this));
							$(
									"input[tabindex="
											+ (parseInt($(this)
													.attr("tabindex"), 10) + 1)
											+ "]").focus();
							return false
						}
						if ($([ 8, 37, 39, 46, 44, 188, 190 ]).index(g.which) >= 0) {
							return true
						}
						return $([ 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 ])
								.index(g.which) >= 0
					}).bind("focusout", function(g) {
				a($(this))
			});
	var a = function(g) {
		var h = parseFloat(f(g.val()));
		if (isNaN(h)) {
			h = ""
		}
		if (h != g.data("last-saved-amount")) {
			g.parent().addClass("loading");
			var i = "daily";
			if (g.hasClass("weekly")) {
				i = "weekly"
			} else {
				if (g.hasClass("monthly")) {
					i = "monthly"
				}
			}
			$.ajax({
				url : "/user/save_target",
				dataType : "json",
				method : "post",
				data : {
					user_id : g.data("user-id"),
					amount : h,
					type : i
				},
				success : function(j) {
					g.parent().removeClass("loading");
					if (j.success) {
						g.data("last-saved-amount", h).parents("td")
								.flashElement("#deffe6", 800)
					} else {
						g.parents("td").flashElement("red", 800)
					}
				},
				error : function(j) {
					g.parent().removeClass("loading");
					g.parents("td").flashElement("red", 800)
				}
			})
		}
	};
	function f(g) {
		var h = $("#converter-element");
		if (!h.length) {
			h = $("<span></span>", {
				"class" : "hidden",
				id : "converter-element",
				html : ""
			}).appendTo($(document.body))
		}
		h.html(g).toNumber({
			region : b
		});
		return $("#converter-element").html()
	}
	function e(g) {
		var i = parseInt(f($(this).val()), 10);
		if (c(i)) {
			var h = (i).format(d.digitGroupSymbol, d.decimalSymbol);
			if (h != $(this).data("old_number")) {
				$(this).val(h).data("old_number", h)
			}
		}
	}
	function c(g) {
		return !isNaN(parseFloat(g)) && isFinite(g)
	}
	$.fn.flashElement = function(g, h) {
		return this.each(function() {
			var j = $(this);
			var i = j.css("background-color");
			if (i === undefined) {
				i = j.css("background")
			}
			j.stop().css("background-color", g).animate({
				backgroundColor : i
			}, h)
		})
	}
};
UI.bindProductList = function() {
	$("a.expanderBtn").live("click", function() {
		var b = $(this).parent();
		var a = b.find(".expander");
		if ($(this).hasClass("expanded")) {
			$(this).html($(this).data("title")).removeClass("expanded");
			a.hide()
		} else {
			a.removeClass("hidden").show();
			$(this).html("Show less").addClass("expanded")
		}
		return false
	});
	$(".product-list tr.expandable a.load-variants").live(
			"click",
			function() {
				var b = $(this).parent().parent();
				var a = b.find("td:first-child").css("backgroundColor");
				b.data("bgColor", a);
				var c = b.data("parent-id");
				if ($(b).hasClass("expanded")) {
					UI.tableActions.removeBlock(c)
				} else {
					UI.tableActions.loadBlock("/variant_detail?id=" + c
							+ "&color=" + a, c, true)
				}
				return false
			});
	$("a.retryHistory").live(
			"click",
			function() {
				var a = $(this).closest(".expandable-child");
				var c = a.data("parent-id");
				var b = a.data("bgColor");
				UI.tableActions.reloadBlock("/variant_detail?id=" + c
						+ "&color=" + b, c, true);
				return false
			});
	$("a.activate").live("click", function() {
		var a = this.href;
		var b = $(this);
		$.ajax({
			url : a,
			dataType : "json",
			data : {},
			success : function(c, e, d) {
				if (d.status == 200) {
					if (c.active === 0) {
						b.html("Activate")
					} else {
						b.html("Deactivate")
					}
					$(this).html("Activate");
					return false
				}
			}
		});
		return false
	})
};
UI.positionModalWindow = function(f) {
	var b;
	if ("undefined" !== typeof f) {
		b = f.find(".ajax-popup-content");
		if (b.length) {
			var h = b.outerHeight(), d = $(".box", b), i = d.length;
			if (i) {
				var c = $(window).height(), e = $(".content", d);
				e.removeAttr("style");
				h = d.outerHeight();
				if (h > c) {
					var g = $(".head", d);
					if (e.length) {
						var a = c
								- (16 + g.outerHeight() + (e.outerHeight() - e
										.height()));
						e.height(a).css("overflow", "auto");
						h = d.outerHeight()
					}
				}
			}
			b.css("marginTop", -(h / 2))
		}
	}
};
UI.bindCheckins = function() {
	var w = $(document.body), z = $("#top-nav .inner"), s = $("#vend-checkin"), t = s
			.find("span.count"), D = $("#paypal-customers"), h = $("#checkin-wrap"), o = $(".checkin-arrow"), e = $("#checkin-window");
	var q = "disabled", m = "change", j = "loading";
	var p = 6, c = 530, y = 140;
	var b, n;
	var l = function() {
		if (!e.length) {
			h = $('<div id="checkin-wrap"></div>').appendTo(w).hide();
			e = $(
					"<div></div>",
					{
						id : "checkin-window",
						style : "top: "
								+ (s.offset().top + s.outerHeight() + p)
								+ "px;"
					}).appendTo(h);
			o = $(
					"<span></span>",
					{
						"class" : "checkin-arrow",
						style : "top: "
								+ (s.offset().top + s.outerHeight() - 4)
								+ "px;"
					}).prependTo(h);
			e.bind("click", function(F) {
				F.stopPropagation()
			});
			e.delegate(".checkin-customer-btn", "click", function(F) {
				a($(this).data("customer-id"));
				return false
			});
			e.delegate(".checkin-refresh, .checkins-try-again", "click",
					function(F) {
						if ($(this).hasClass("checkins-try-again")) {
							s.addClass("loading")
						}
						if (!e.hasClass(j)) {
							e.html("");
							k()
						}
						return false
					});
			e.delegate(".checkin-back", "click", function(F) {
				e.html("");
				D.appendTo(e);
				v();
				return false
			});
			e.delegate(".add-to-sale", "click", function(F) {
				F.preventDefault();
				window.location = "/sell#customer-add/"
						+ $(this).data("customer-id");
				h.hide();
				return false
			});
			$(document.body).bind(
					"click",
					function(F) {
						if (F.target.id != "vend-checkin"
								&& F.target.id != "vend-checkin-count") {
							if (h.is(":visible")) {
								h.hide()
							}
						}
					})
		}
	};
	var v = function() {
		if (Is.NativeClient) {
			var H = 880;
			h.css({
				display : "block",
				height : "704px",
				left : 0,
				margin : 0,
				top : 0,
				width : "1024px"
			});
			e.css({
				left : (H - (e.outerWidth() / 2)) + "px",
				top : "11px"
			});
			o.css({
				left : (H - (o.outerWidth() / 2)) + "px",
				top : 0
			});
			return
		}
		var F = z.offset().left, G = (t.offset().left - F)
				+ (t.outerWidth() / 2);
		e.css("left", 0).css("left", (G - (e.outerWidth() / 2)) + "px");
		o.css("left", (G - (o.outerWidth() / 2)) + "px");
		e.hide(0, function() {
			e.show()
		})
	};
	var E = function() {
		if ("undefined" == typeof e || !e.length) {
			l()
		}
		return e
	};
	var g = function() {
		if (!D.length) {
			D = $('<div id="paypal-customers"></div>');
			D.appendTo(E())
		}
	};
	var f = function() {
		if (!D.length) {
			g()
		}
		return D
	};
	var i = function(F) {
		if ("undefined" !== typeof F && F) {
			s.addClass(j)
		}
		$.ajax({
			url : "/paypal/status",
			dataType : "json",
			success : function(G) {
				C(G)
			}
		})
	};
	var C = function(F) {
		s.removeClass(j);
		if (F.hasCheckin) {
			if (s.hasClass(q)) {
				s.removeClass(q);
				t.html("0");
				VendPOS.NativeBridge.setCheckedInCount(0);
				if (E().hasClass("initialize") && E().hasClass(j)) {
					E().removeClass(j);
					k()
				}
			}
			if (parseInt(F.customerCount, 10) > 0) {
				if (parseInt(t.html(), 10) != parseInt(F.customerCount, 10)) {
					w.removeData("paypal-customers-ttl");
					if (!s.hasClass(m)) {
						s.addClass(m)
					}
				}
				if (f().is(":visible") && !E().hasClass(j)) {
					s.removeClass(m)
				}
			} else {
				if (s.hasClass(m)) {
					s.removeClass(m)
				}
			}
			t.html(F.customerCount);
			VendPOS.NativeBridge.setCheckedInCount(F.customerCount)
		} else {
			B();
			if (!s.hasClass(q)) {
				s.addClass(q);
				t.html("&nbsp;");
				VendPOS.NativeBridge.setCheckedInCount(null)
			}
		}
		E().removeClass("initialize")
	};
	var k = function() {
		var F = w.data("paypal-customers-ttl");
		if ("undefined" !== typeof F) {
			if (F > x()) {
				r();
				return
			}
		}
		if ("undefined" !== typeof n) {
			n.abort()
		}
		E().addClass(j).parent().show();
		v();
		n = $.ajax({
			url : "/paypal/customers",
			dataType : "json",
			success : function(G) {
				E().removeClass(j);
				d(G)
			},
			error : function(G) {
			}
		})
	};
	var d = function(F) {
		if (parseInt(F.customerCount, 10) > 0) {
			w.data("paypal-customers-ttl", (x() + 6))
		}
		if ("" !== F.html) {
			f().html(F.html);
			r()
		}
		C(F)
	};
	var r = function() {
		if (E().is(":visible")) {
			f().appendTo(E().html(""));
			h.show();
			v()
		}
	};
	var a = function(F) {
		f().detach();
		if ("undefined" !== typeof n) {
			n.abort()
		}
		E().addClass(j);
		v();
		n = $.ajax({
			url : "/paypal/customerDetails?id=" + F,
			success : function(G) {
				E().removeClass(j);
				u(G)
			},
			error : function(G) {
			}
		})
	};
	var u = function(F) {
		E().html(F);
		v()
	};
	var B = function() {
		E()
				.removeClass("loading")
				.html(
						'<div class="error-holder"><p class="text-center disabled-message">Unable to connect to PayPal Here.</p></div><p class="text-center"><a href="#" class="checkins-try-again button-1 small-button dark">Try again.</a></p>');
		v()
	};
	var A = function() {
		$.ajax({
			url : "/paypal/initialize",
			dataType : "json",
			success : function(F) {
				if (F.success) {
					i(true);
					s.fadeIn("fast");
					b = setInterval(i, 20000)
				}
			}
		})
	};
	var x = function() {
		return Math.round((new Date()).getTime() / 1000)
	};
	$("#vend-checkin").live("click", function(F) {
		F.stopPropagation();
		E().parent().toggle().end();
		if (E().parent().is(":visible")) {
			v();
			if (s.hasClass(m)) {
				s.removeClass(m)
			}
			e.html("");
			if (!$(this).hasClass(q)) {
				k()
			} else {
				if (!$(this).hasClass(j)) {
					B()
				} else {
					E().addClass("loading initialize")
				}
			}
		}
		return false
	});
	if (Is.NativeClient) {
		$(window).bind("showCheckIns", function() {
			var F = E(), G = $("#checkin-wrap");
			if (G.is(":visible")) {
				G.hide();
				return
			}
			F.html("");
			F.addClass("loading initialize");
			v();
			k()
		});
		$(window).bind("hideCheckIns", function() {
			$("#checkin-wrap").hide()
		})
	}
	A()
};
UI.bindSalesHistory = function() {
	if (window.location.hash) {
		var c = $(".expandable[data-parent-id="
				+ window.location.hash.substring(1) + "]");
		if (c.length) {
			var b = c.data("parent-id");
			UI.tableActions.loadBlock("/sale_detail?id=" + b, b)
		}
	}
	var a = function(g) {
		if (g.target.tagName != "A" || !$(g.target).hasClass("clickable")) {
			var f = $(this);
			var h = f.data("parent-id");
			if ($(f).hasClass("expanded")) {
				UI.tableActions.removeBlock(h)
			} else {
				UI.tableActions.loadBlock("/sale_detail?id=" + h, h)
			}
			return false
		}
	};
	$(".sales-history tr.expandable").bind("tap click", a);
	$(".sale-detail a.delete").live("tap click", function() {
		var e = $(this).closest(".expandable-child").data("parent-id");
		$.ajax({
			url : $(this).attr("href"),
			dataType : "json",
			success : function(f) {
				if (f.success) {
					UI.tableActions.reloadBlock("/sale_detail?id=" + e, e)
				} else {
					alert(f.error)
				}
			}
		});
		return false
	});
	$("a.retryHistory").live("tap click", function() {
		var e = $(this).closest(".expandable-child").data("parent-id");
		UI.tableActions.reloadBlock("/sale_detail?id=" + e, e);
		return false
	});
	$("a.apply-payment").live("tap click", function() {
		$.ajax({
			url : $(this).attr("href"),
			dataType : "json",
			success : function(e) {
				if (e.success) {
					$("#dialog-message").html(e.html);
					$("#dialog-message").dialog({
						modal : true,
						width : 500
					});
					d()
				}
			}
		});
		return false
	});
	$("#dialog-message a.btn-cancel").live(Register.TRIGGER, function() {
		$("#dialog-message").dialog("close");
		return false
	});
	$("#dialog-message button[value=save]")
			.live(
					"click",
					function() {
						var f = $(this).parents("form");
						var e = {
							"vend_register_sale_payment[register_sale_id]" : f
									.find(
											"#vend_register_sale_payment_register_sale_id")
									.val(),
							"vend_register_sale_payment[id]" : f.find(
									"#vend_register_sale_payment_id").val(),
							"vend_register_sale_payment[retailer_payment_type_id]" : f
									.find(
											"#vend_register_sale_payment_retailer_payment_type_id")
									.val(),
							"vend_register_sale_payment[amount]" : f.find(
									"#vend_register_sale_payment_amount").val(),
							"vend_register_sale_payment[payment_date]" : f
									.find(
											"#vend_register_sale_payment_payment_date")
									.val(),
							"vend_register_sale_payment[payment_time][hour]" : f
									.find(
											"#vend_register_sale_payment_payment_time_hour")
									.val(),
							"vend_register_sale_payment[payment_time][minute]" : f
									.find(
											"#vend_register_sale_payment_payment_time_minute")
									.val(),
							"vend_register_sale_payment[register_id]" : f.find(
									"#vend_register_sale_payment_register_id")
									.val()
						};
						$.post(f.attr("action"), e, function(g) {
							if (g.saved) {
								var h = f.data("sale-id");
								$("#dialog-message").dialog("close");
								if (g.status) {
									$(
											".expandable[data-parent-id=" + h
													+ "] .status").text(
											g.status)
								}
								UI.tableActions.reloadBlock("/sale_detail?id="
										+ h, h)
							} else {
								$("#dialog-message").html(g.html);
								d()
							}
						}, "json");
						return false
					});
	function d() {
		$(".datepicker").datepicker({
			showOn : "focus",
			buttonImage : "/sf/sf_admin/images/date.png",
			buttonImageOnly : true,
			dateFormat : "d MM, yy",
			yearRange : "-5:+0",
			minDate : "-5 years",
			gotoCurrent : true,
			changeMonth : true,
			changeYear : true,
			showAnim : "slideDown",
			beforeShow : function() {
				$("#ui-datepicker-div").maxZIndex()
			},
			duration : "fast"
		})
	}
};
UI.tableActions = (function() {
	return {
		removeBlock : function(b) {
			var a = $("tr.expandable-child[data-parent-id=" + b + "]");
			$sale_detail = a.find(".sale-detail");
			if ($sale_detail.length) {
				$sale_detail.slideUp(350, function() {
					a.remove();
					$(".expandable[data-parent-id=" + b + "]").removeClass(
							"expanded")
				})
			} else {
				a.remove();
				$(".expandable[data-parent-id=" + b + "]").removeClass(
						"expanded")
			}
		},
		loadBlock : function(b, g, a) {
			var e = this;
			var d = $(".expandable[data-parent-id=" + g + "]");
			var f = $("<tr></tr>", {
				"data-parent-id" : g,
				"class" : "expandable-child loading"
			});
			var c = $("<td></td>", {
				colspan : d.parents("table").find("th").length,
				"class" : "expandable-child-td"
			}).appendTo(f);
			f.insertAfter(d).find("td").css("padding", "0").animate({
				padding : "50px"
			}, 350);
			d.addClass("expanded");
			e.getBlockByAJAX(b, g, f, a)
		},
		reloadBlock : function(b, e, a) {
			var d = this;
			var c = $(".expandable-child[data-parent-id=" + e + "]").addClass(
					"reloading");
			d.getBlockByAJAX(b, e, c, a)
		},
		getBlockByAJAX : function(b, d, c, a) {
			$
					.ajax({
						url : b,
						success : function(h) {
							c.removeClass("loading").removeClass("reloading");
							if (a) {
								c.replaceWith(h)
							} else {
								var i = c.find("td");
								var e = i.outerHeight();
								i.css({
									padding : 0
								});
								var g = i.stop().html(h).find(".sale-detail");
								var f = g.outerHeight();
								g.css("height", e + "px").animate({
									height : f + "px"
								}, 350, function() {
									g.css("height", "auto")
								})
							}
						},
						error : function(f) {
							c.removeClass("loading").addClass("error");
							var e = '<em>Argh, there was an error! Please try again in a moment: <a href="#" class="retryHistory">Retry</a></em>';
							if (c.data("failures") === ""
									|| c.data("failures") === undefined) {
								c.data("failures", 1)
							} else {
								c.data("failures", parseInt(c.data("failures"),
										10) + 1);
								if (c.data("failures") > 1) {
									e += '<em class="failure-notice">If this problem persists, please <a href="mailto:help@vendhq.com">contact our support team</a>'
								}
							}
							c.find("td").stop().css("text-align", "center")
									.html(e)
						}
					})
		}
	}
})();
UI.bindReportFilters = function() {
	$(".toggle-more-filters").bind("click", function() {
		if ($(this).hasClass("open")) {
			$("div.more-filters").stop().slideUp(250);
			$(this).html("More Filter Options").removeClass("open")
		} else {
			$("div.more-filters").stop().slideDown(250);
			$(this).html("Less Filter Options").addClass("open")
		}
		return false
	})
};
UI.scrollableTable = function() {
	var s = [ "total", "odd", "even", "section-header", "blank", "last",
			"child" ];
	function c(u) {
		return u.outerHTML || (function(x) {
			var w = document.createElement("div"), v;
			x.appendTo(w);
			v = w.innerHTML;
			w = null;
			return v
		})(u)
	}
	var t = function(w, u) {
		var v = "";
		if (u.hasClass("section-header") || $(w).hasClass("blank")) {
			var x = $(w).attr("colspan");
			if ("undefined" !== typeof x) {
				x = parseInt(x, 10) - 1
			}
			if (x > 0) {
				$(w).attr("colspan", 1);
				$(w)
						.after(
								'<td colspan="' + x + '" class="' + b($(w))
										+ '"></td>')
			}
		}
		v = "<th "
				+ ("undefined" !== typeof $(w).attr("width") ? 'width="'
						+ $(w).attr("width") + '"' : "") + ">" + $(w).html()
				+ "</th>";
		$(w).addClass("hidden");
		return v
	};
	var b = function(v) {
		var u = "";
		s.each(function(x, w) {
			if (v.hasClass(x)) {
				if (u !== "") {
					u += " "
				}
				u += x
			}
		});
		return u
	};
	var f = function(w) {
		var v = $(w).parent(), u = "";
		u += t(w, v);
		return '<tr class="' + b(v) + '">' + u + "</tr>"
	};
	var h = function(y, x) {
		var w = $("<thead></thead>");
		var v = $("<tbody></tbody>");
		var u = null;
		y.each(function(z, A) {
			target = v;
			if (z === 0) {
				target = w
			}
			target.append(f(A))
		});
		u = $("<table></table>", {
			"class" : "item_list static"
					+ (x.hasClass("no-stripe") ? " no-stripe" : "")
					+ (x.hasClass("sales-by-hour") ? " sales-by-hour" : "")
					+ (x.hasClass("headers-sortable") ? " headers-sortable"
							: ""),
			html : c(w) + c(v)
		});
		return c(u)
	};
	var e = function(C, w) {
		var z = [];
		var B = 0;
		var D = C.find("tr");
		D.each(function() {
			var E = $(this)
					.find("th:not(.hidden):first, td:not(.hidden):first");
			z[B] = E[0];
			B++
		});
		var A = null, y = null, u = 0, x = 0, v = $.browser;
		w.find("th:first-child").each(function(E, F) {
			y = $(F);
			A = $(z[E]);
			x = y.outerHeight();
			u = A.outerHeight();
			if (u > x) {
				if (y.parent().parent().attr("tagName") == "THEAD") {
					u -= 1
				}
				if (v.mozilla) {
					u += 1
				}
				y.css({
					paddingTop : 0,
					paddingBottom : 0
				}).height(u - 1)
			} else {
				A.parents("tr").css({
					paddingTop : 0,
					paddingBottom : 0
				}).height(x)
			}
		})
	};
	var q = $(".static").removeClass("static").addClass("static-aside"), g = (q
			.find(".section-header").length || q.hasClass("wide-left"))
			&& !q.hasClass("register-sales-detail") ? 250 : 140, i = q
			.find("tr th:first-child, tr td:first-child"), r = q.find(
			"thead th:first-child").outerWidth();
	r = r < g ? g : r;
	var k = Math.round(((r / 960) * 100) * 100) / 100, n = h(i, q), m = $(
			"<div></div>", {
				style : "width: " + k + "%",
				"class" : "left",
				html : n
			});
	var p = (100 - k);
	q
			.wrap('<div class="scroll table-scroll scroll-start" style="width: 100%;"></div>');
	var l = q.parent();
	l.wrap('<div class="relative left table-scroll-wrap" style="width: ' + p
			+ '%;"></div>');
	l.parent().before(m);
	$static = $("table.static");
	q.find("thead th").each(function(v, y) {
		if ($(y).hasClass("make-left")) {
			var x = $(this).parent();
			var w = t(y, x);
			var u = 0;
			$static.find("thead tr").append(w);
			q.find("tbody tr").each(function() {
				var z = $(this).find("td.hidden").length;
				w = t($(this).find("td:eq(" + v + ")"), x);
				$static.find("tbody tr:eq(" + u + ")").append(w);
				u++
			});
			m.css("width", (k + 10) + "%");
			q.parent().parent().css("width", (p - 10) + "%")
		}
	});
	e(q, m);
	q.removeClass("invisible");
	var a = l.find("table").outerWidth() - l.innerWidth();
	var d = 0;
	var j = $("<div></div>", {
		"class" : "absolute removed scrollShadowLeft",
		style : "height: " + l.innerHeight() + "px;"
	}).appendTo(l.parent());
	var o = $("<div></div>", {
		"class" : "absolute scrollShadowRight",
		style : "height: " + l.innerHeight() + "px;"
	}).appendTo(l.parent());
	l.scroll(function(v) {
		d = l.scrollLeft();
		if ((d + 5) >= a) {
			var w = a - d;
			var u = (5 - w);
			if (u > 5) {
				u = 5
			}
			l.css("borderTopRightRadius", (u) + "px")
		} else {
			l.css("borderTopRightRadius", "0px")
		}
		if (d >= a) {
			o.addClass("removed")
		} else {
			o.removeClass("removed")
		}
		if (d <= 0) {
			j.addClass("removed")
		} else {
			j.removeClass("removed")
		}
	})
};
UI.createBarHighChart = function(c, b, a) {
	window.barChart = new Highcharts.Chart({
		credits : {
			enabled : false
		},
		chart : {
			renderTo : c,
			defaultSeriesType : "column",
			plotBackgroundColor : {
				linearGradient : [ 0, 0, 0, 250 ],
				stops : [ [ 0, "rgb(255, 255, 255)" ],
						[ 1, "rgb(231, 241, 230)" ] ]
			}
		},
		legend : {
			enabled : false
		},
		title : {
			text : ""
		},
		labels : {
			style : {
				color : "#aeaeae",
				size : "10px"
			}
		},
		plotOptions : {
			column : {
				borderColor : "#3d9c34",
				color : "rgba(60, 155, 55, 0.35)",
				pointPadding : 0.1,
				groupPadding : 0,
				shadow : false
			}
		},
		yAxis : {
			title : "",
			labels : {
				style : {
					color : "#aeaeae",
					size : "9px"
				}
			},
			tickColor : "#a0a29f",
			tickWidth : 1,
			gridLineColor : "#d7d7d7",
			gridLineWidth : 1,
			lineWidth : 1,
			lineColor : "#a0a29f"
		},
		xAxis : {
			categories : a,
			labels : {
				y : 20,
				style : {
					color : "#aeaeae",
					size : "9px"
				}
			},
			tickColor : "#d7d7d7",
			gridLineColor : "#d7d7d7",
			gridLineWidth : 1
		},
		tooltip : {
			borderColor : "#3e9b33",
			formatter : function() {
				return "<b>" + this.x + "</b><br/>" + (this.y).format()
						+ " sold"
			}
		},
		series : [ {
			name : "Popular Products",
			data : b
		} ]
	})
};
UI.createLineHighChart = function(e, d, a) {
	var b = window.culture.symbol;
	var c = [];
	$.each(d, function(f, g) {
		c.push([ Date.UTC(g.year, g.month, g.day), g.total ])
	});
	window.lineChart = new Highcharts.Chart({
		credits : {
			enabled : false
		},
		chart : {
			renderTo : e,
			plotBackgroundColor : {
				linearGradient : [ 0, 0, 0, 250 ],
				stops : [ [ 0, "rgb(255, 255, 255)" ],
						[ 1, "rgb(231, 241, 230)" ] ]
			}
		},
		title : {
			text : ""
		},
		labels : {
			style : {
				color : "#aeaeae",
				size : "10px"
			}
		},
		legend : {
			enabled : false
		},
		xAxis : {
			type : "datetime",
			labels : {
				y : 30,
				rotation : -45,
				style : {
					color : "#aeaeae",
					size : "10px"
				}
			},
			tickColor : "#d7d7d7",
			tickInterval : a,
			gridLineColor : "#d7d7d7",
			gridLineWidth : 1,
			showLastLabel : false,
			showFirstLabel : false
		},
		plotOptions : {
			line : {
				color : "#3e9b33",
				shadow : false
			}
		},
		tooltip : {
			borderColor : "#3e9b33",
			formatter : function() {
				var g = new Date(this.x);
				var f = dateFormat(g, "UTC:ddd, dS mmmm yyyy");
				return "<b>" + f + "</b><br/>" + b + (this.y).format()
			}
		},
		point : {
			color : "#3e9b33"
		},
		yAxis : {
			title : "",
			labels : {
				formatter : function() {
					if (this.value < 0) {
						return "(" + b + (this.value * -1).format() + ")"
					} else {
						if (this.value !== 0) {
							return b + this.value.format()
						}
					}
					return this.value
				},
				style : {
					color : "#aeaeae",
					size : "9px"
				}
			},
			gridLineColor : "#d7d7d7",
			gridLineWidth : 1,
			lineWidth : 1,
			lineColor : "#a0a29f",
			tickColor : "#a0a29f",
			tickWidth : 1,
			startOnTick : false,
			minPadding : 0.2,
			maxPadding : 0.2
		},
		series : [ {
			name : "Sales",
			data : c
		} ]
	})
};
UI.filters = function() {
	$(".filter a").each(
			function(a) {
				var b = $(this);
				b.bind("click", function() {
					if (!$(this).hasClass("selected")) {
						$(this).parent().parent().find("a.selected")
								.removeClass("selected");
						$(this).addClass("selected");
						var c = b.attr("target");
						$("#" + c).html('<div class="loading chart"></div>');
						$.ajax({
							url : $(this).attr("href"),
							success : function(d) {
								$("#" + c).html(d)
							}
						})
					}
					return false
				})
			})
};
UI.dropdowns = (function() {
	var c = 1000;
	var f = 0;
	var e = 0;
	var d;
	var b;
	var a;
	d = function() {
		f = window.setTimeout(a.ddClose, c)
	};
	b = function() {
		if (f) {
			window.clearTimeout(f);
			f = null
		}
	};
	return {
		init : function() {
			a = this;
			$(".dropdown").each(function(h) {
				var g = this;
				$(g).find(".dropdown-selector").bind("click", function() {
					$(this).parent().bind("mouseover", function() {
						b()
					});
					a.ddClose();
					a.ddOpen(g);
					return false
				})
			});
			document.onclick = a.ddClose
		},
		ddOpen : function(g) {
			b();
			e = $(g).find(".dropdown-list");
			e.show()
		},
		ddClose : function() {
			if (e) {
				e.hide()
			}
		}
	}
})();
UI.initModalWindows = function(d, a) {
	var b = $(".ajax-popup-content-holder");
	var c = $(".ajax-popup-content");
	$(".modal").live("click", function() {
		$.ajax({
			url : $(this).attr("href"),
			success : function(e) {
				c.html(e);
				b.show();
				UI.positionModalWindow(b);
				if ("function" == typeof d) {
					d()
				}
			}
		});
		return false
	});
	$(".btn-cancel").live(Register.TRIGGER, function() {
		b.hide();
		return false
	});
	$(".ajax-popup-content form").live(
			"submit",
			function() {
				if (!$(this).hasClass("not-ajax")) {
					var e = {
						dataType : "json",
						success : function(g, f) {
							b.hide();
							if ("function" == typeof a) {
								a(g)
							} else {
								window.location.reload(true)
							}
							$(this).find(".modal-button.once").removeAttr(
									"disabled").removeClass("disabled")
						},
						error : function(g, f) {
							c.html(g.responseText);
							$(this).find(".modal-button.once").removeAttr(
									"disabled").removeClass("disabled")
						}
					};
					$(this).ajaxSubmit(e);
					$(this).find(".modal-button.once").attr("disabled", true)
							.addClass("disabled");
					return false
				}
			})
};
UI.fileUploader = (function() {
	return {
		init : function() {
			var a = this;
			$(".file_uploader_holder").each(
					function(b) {
						var c = $(this).find(".psudo_file_input");
						$(this).find(".uploader input.file_upload").bind(
								"change", function(d, e) {
									c.val($(this).val())
								})
					})
		}
	}
})();
UI.datepicker = (function() {
	return {
		init : function() {
			$(".datepicker").each(function(a) {
				var c = $(this);
				var b = c.val();
				c.datepicker({
					showOn : "focus",
					buttonImage : "/sf/sf_admin/images/date.png",
					buttonImageOnly : true,
					dateFormat : "dd MM, yy",
					yearRange : "-5:+0",
					minDate : "-5 years",
					gotoCurrent : true,
					changeMonth : true,
					changeYear : true,
					showAnim : "slideDown",
					beforeShow : function() {
						$("#ui-datepicker-div").maxZIndex()
					},
					duration : "fast"
				});
				if (c.hasClass("max_today")) {
					c.datepicker("option", "maxDate", "+0d")
				}
				if (c.hasClass("min_today")) {
					c.datepicker("option", "minDate", "+0d");
					c.datepicker("option", "yearRange", "+0:+5")
				}
				if (c.hasClass("broad")) {
					c.datepicker("option", "yearRange", "-5:+5")
				}
				c.blur();
				if (typeof b == "string" & b !== "") {
					c.val(dateFormat(b, "dd mmmm, yyyy"))
				}
			})
		}
	}
})();
UI.datetimepicker = (function() {
	return {
		init : function() {
			$(".datetimepicker").each(function(a) {
				var b = $(this);
				var c = b.val();
				b.datetimepicker({
					showOn : "focus",
					ampm : true,
					buttonImage : "/sf/sf_admin/images/date.png",
					buttonImageOnly : true,
					dateFormat : "d MM yy",
					timeFormat : "hh:mmtt",
					yearRange : "-5:+0",
					minDate : "-5 years",
					gotoCurrent : true,
					changeMonth : true,
					changeYear : true,
					showAnim : "slideDown",
					beforeShow : function() {
						$("#ui-datepicker-div").maxZIndex()
					},
					duration : "fast"
				});
				if (b.hasClass("max_today")) {
					b.datepicker("option", "maxDate", "+0d")
				}
				if (b.hasClass("min_today")) {
					b.datepicker("option", "minDate", "+0d")
				}
				b.val(dateFormat(c, "dd mmmm, yyyy hh:MMtt"))
			})
		}
	}
})();
UI.selectOrAdd = (function() {
	var a;
	return {
		init : function() {
			var e = this;
			var c = {};
			var b = {};
			var f = null;
			var d = null;
			$(".ajax-popup-holder").each(
					function(g) {
						var h = $(this);
						$(this).find(".ajax-popup").bind(
								"click",
								function() {
									e.holder = $(this).parent().find(
											".ajax-popup-content-holder");
									e.holder_content = e.holder
											.find(".ajax-popup-content");
									$.ajax({
										url : $(this).attr("href"),
										success : function(i) {
											e.holder.show();
											e.holder_content.html("").append(
													$(i));
											e._prepareContent(e.holder);
											UI.positionModalWindow(e.holder)
										}
									});
									return false
								});
						$(this).find(".ajax-popup-select").bind("change",
								function() {
									if ($(this).val() == "add-new") {
										h.find(".ajax-popup").trigger("click")
									}
								});
						h.find(".ajax-popup").hide()
					})
		},
		_prepareContent : function(b) {
			var c = this;
			var d = b.find("form");
			d.bind("submit", function() {
				var e = {
					dataType : "json",
					success : function(g, f) {
						b.parent().find("input").val(g.code);
						b.parent().find("select").append(
								$('<option selected="selected"></option>')
										.attr("value", g.id).text(g.name));
						b.hide()
					},
					error : function(g, f) {
						c.holder.find(".ajax-popup-content").html(
								g.responseText);
						c._prepareContent(b)
					}
				};
				$(this).ajaxSubmit(e);
				return false
			});
			b.find(".btn-cancel").bind(Register.TRIGGER, function() {
				b.hide();
				return false
			})
		}
	}
})();
UI.productSearchWidget = (function() {
	return {
		init : function(b, a, e, k, d) {
			var h = this, i = $("#product-search"), g = $("#product_search_quantity"), c = $("#product_search_product_sku"), m = $("#product_search_product_id"), o = $("#btn-add-products"), f = o.length ? true
					: false;
			$.each([ c, g ], function(p, q) {
				q.bind("keydown", function(r) {
					h.lastKeyPressCode = r.keyCode;
					if (r.keyCode == 13 && f) {
						if (i.is("form")) {
							i.trigger("submit")
						} else {
							i.triggerHandler("product:add")
						}
						return false
					}
				})
			});
			if (f) {
				o.bind(Register.TRIGGER, function(p) {
					p.preventDefault();
					if (i.is("form")) {
						i.trigger("forceSubmit")
					} else {
						i.triggerHandler("product:add")
					}
					return false
				})
			}
			$("#main-body").bind("click", function() {
				var p = $("*:focus").attr("id");
				if (typeof p == "undefined") {
					c.select().blur().focus()
				}
			});
			i
					.bind(
							"submit product:add",
							function(v) {
								var u = $(document.body)
										.children(".ac_results").eq(0), w = parseFloat(g
										.val()), t = c.val(), s = m.val(), p = function() {
									g.val(1);
									m.val("");
									c.val("");
									if (!Is.iOS) {
										var x = function() {
											var y = 0;
											if (c.hasClass("ac_loading")) {
												var z = function() {
													if (c
															.hasClass("ac_loading")
															&& y < 300) {
														y += 50;
														setTimeout(z, 50);
														return
													}
													debug("DELAYED RESET");
													c.removeClass("ac_loading")
															.select().blur()
															.focus()
												};
												setTimeout(z, 50)
											}
											c.select().blur().focus()
										}()
									}
								}, r = function(x) {
									w = isNaN(w) ? 1 : w;
									_addProduct(x, w, k, d);
									p()
								};
								if (u.length && u.is(":visible")) {
									return false
								}
								if (t.length) {
									if (s.length) {
										r(s)
									} else {
										var q = b + "&q=" + t;
										$.ajax({
											url : q,
											dataType : "json",
											success : function(x) {
												if (x.products.length > 0) {
													r(x.products[0].id)
												}
											}
										})
									}
								} else {
									p()
								}
								v.preventDefault();
								return false
							});
			var l = function(p) {
				return [
						'<span class="name">' + p.name + "</span>",
						'<div class="details">',
						'<span class="handle">Handle: ' + p.handle
								+ "</span> / ",
						'<span class="sku">SKU: ' + p.sku + "</span>", "</div>" ]
						.join("")
			};
			var j = function(r) {
				var q = r;
				var p = function() {
					m.val(q.id);
					return q.sku
				};
				return p
			};
			_updatePaginator = function() {
				var p = $("#" + a + "-items .item_list tbody tr").length;
				$("div.paginator span.nb-results").html(p)
			};
			_addProduct = function(r, u, v, t) {
				debug("****ADD PRODUCT", arguments);
				if (t) {
					$("#id-" + r).remove()
				}
				var q = $("#" + a + "-items"), p = $(".item_list tbody", q), s = $('<tr><td colspan="99" class="loading">Updating...</td></tr>');
				$(".empty_text", q).remove();
				p.prepend(s);
				$.ajax({
					url : e + "&type=product&product_id=" + r + "&quantity="
							+ u,
					dataType : "html",
					success : function(w) {
						var x = $(w);
						UI.bindDelete(x, function(y) {
							_updatePaginator();
							c.select().blur().focus();
							v(y)
						});
						if (t) {
							$("#id-" + r).remove()
						}
						s.replaceWith(x);
						v(x)
					}
				});
				_updatePaginator();
				q.attr({
					scrollTop : 0
				})
			};
			c.bind("result", function(p, r, q) {
				m.val(r.id);
				c.val(r.sku);
				if (13 === h.lastKeyPressCode) {
					i.trigger("submit")
				} else {
					g.focus()
				}
			});
			var n = {
				minChars : 2,
				width : 320,
				max : 100,
				scroll : true,
				scrollHeight : 300,
				cacheLength : 0,
				matchContains : "word",
				autoFill : false,
				delay : 200,
				dataType : "json",
				formatItem : l,
				formatResult : j,
				parse : function(r) {
					var p = [];
					var s = r.products;
					for ( var q = 0; q < s.length; q++) {
						s[q].type = "product";
						p[p.length] = {
							data : s[q],
							value : s[q].name,
							result : n.formatResult && n.formatResult(s[q])
						}
					}
					return p
				}
			};
			c.autocomplete(b, n).select().blur().focus().trigger("click")
		}
	}
})();
UI.customerSearchWidget = (function() {
	return {
		init : function(b, a, c, h) {
			var e = this;
			var d;
			var i;
			var f;
			var g = function(p) {
				var k = [];
				var m = p.customers;
				for ( var n = 0, l = m.length; n < l; n++) {
					var o = m[n];
					k[k.length] = {
						data : o,
						value : o.name,
						result : j.formatResult && j.formatResult(o, o.name)
								|| o.name
					}
				}
				return k
			};
			i = function(l) {
				var k = l;
				return '<span class="name">' + k.name
						+ '</span><span class="code">(' + k.customer_code
						+ ')</span><br/><span class="company_name">'
						+ k.company_name + "</span>"
			};
			f = function(l) {
				var k = l;
				return function() {
					c(k);
					return k.name
				}
			};
			var j = {
				minChars : 3,
				width : 320,
				max : 10,
				highlight : false,
				scroll : true,
				scrollHeight : 300,
				cacheLength : 0,
				matchContains : "word",
				dataType : "json",
				autoFill : false,
				delay : 200,
				parse : g,
				formatItem : i,
				formatResult : f
			};
			$("#" + a).autocomplete(b, j).result(function(k, m, l) {
				return false
			})
		}
	}
})();
UI.tagAdder = (function() {
	var a;
	return {
		init : function() {
			var c = this;
			var b = {};
			var d = null;
			$(".ajax-tag-holder").each(function(f) {
				var g = $(this);
				var e = g.find("input:text");
				e.keydown(function(h) {
					if (h.keyCode === 13) {
						(function() {
							g.find(".ajax-tag-submit").click()
						}).delay(25);
						return false
					}
				});
				c.setupAutocomplete(e, "/category/nameautocomplete");
				c.bind_deletes(g.find(".ajax-tags-content-holder"));
				g.find(".ajax-tag-submit").bind("click", function() {
					var h = $(this);
					c.holder_content = g.find(".ajax-tags-content-holder");
					$.ajax({
						type : "POST",
						url : h.attr("href"),
						data : {
							tag : e.val()
						},
						success : function(k) {
							e.val("");
							var i = $(k).find("input").attr("id");
							var j = false;
							c.holder_content.find("span.tag").each(function() {
								var l = $(this);
								if (l.find("input").attr("id") == i) {
									j = l
								}
							});
							if (j === false) {
								c.holder_content.append(k);
								c.bind_deletes(c.holder_content)
							} else {
								j.addClass("highlight")
							}
						}
					});
					return false
				})
			})
		},
		setupAutocomplete : function(b, f) {
			var g = this;
			var e = function(i) {
				var h = JSON.parse(i);
				return h.name
			};
			var d = function(i) {
				var h = JSON.parse(i);
				return '<span class="autocomplete-row">' + h.name + "</span>"
			};
			var c = {
				minChars : 3,
				width : 320,
				max : 4,
				highlight : false,
				scroll : true,
				scrollHeight : 300,
				cacheLength : 1000,
				autoFill : false,
				delay : 200,
				formatResult : e,
				formatItem : d
			};
			b.autocomplete(f, c)
		},
		bind_deletes : function(b) {
			b.find("a").each(function() {
				var c = $(this);
				c.bind("click", function() {
					if (c.attr("href").length > 1) {
						$.ajax({
							url : c.attr("href"),
							success : function(d) {
								c.parent().remove()
							}
						})
					} else {
						c.parent().remove()
					}
					return false
				})
			})
		}
	}
})();
UI.PaymentReceipt = function(d, f, c) {
	if (typeof d === "undefined" || typeof c === "undefined") {
		throw new Error("UI.PaymentReceipt requires a status and an amount")
	}
	c = c || {};
	var g = $("#receipt", document.body), a = [ "header", "customer", "meta" ], h = "", j;
	for ( var e = 0, b = a.length; e < b; e++) {
		h = h + '<div id="receipt-' + a[e] + '">'
				+ $("#receipt-" + a[e], g).html()
				+ '</div><div class="clearer"></div>'
	}
	this.$el = $(this.template.replace("{content}", h));
	this.el = this.$el[0];
	j = this.$("#receipt-meta-extra");
	this.$("#receipt-status").text(d);
	this.$("#receipt-transaction-amount").text("Amount: " + f);
	for ( var k in c) {
		if (c.hasOwnProperty(k)) {
			j.append("<p>" + k + ": " + c[k] + "</p>")
		}
	}
};
$
		.extend(
				UI.PaymentReceipt.prototype,
				{
					template : '<div id="receipt"><div class="clearer"></div>{content}<div id="receipt-meta-extra"></div><div class="clearer"></div><p id="receipt-transaction-amount"></p><div class="clearer"></div><p id="receipt-status"></><div class="clearer"></div><p id="receipt-copy">Customer copy</p></div>',
					print : function() {
						Register.print(this.$el)
					},
					$ : function(a) {
						return $(a, this.$el)
					}
				});
var Signin = (function() {
	return {
		_datastore : {},
		init : function(b) {
			var a = this;
			a._datastore = Datastore;
			a._datastore.checkOfflineCapabilities(function() {
				if (a._datastore.offline_supported) {
					var c = function(e) {
						if (e && e.domain_prefix) {
							$("#container").hide();
							if (window.local_dev) {
								window.location = "http://" + e.domain_prefix
										+ ".local.vendhq.com"
							} else {
								window.location = "https://" + e.domain_prefix
										+ ".vendhq.com"
							}
						} else {
							$("#signin-form").show()
						}
					};
					var d = window.location.hash.substring(1).split("/");
					if (d == "reset") {
						a._datastore.store.dropAccount()
					}
					a._datastore.store.initStoreAccount(function() {
						debug("INIT account");
						a._datastore.store.getSigninAccount(c)
					})
				}
			});
			debug("Init");
			a.bindSelectDomain($("#select-domain"))
		},
		bindSelectDomain : function(b) {
			var a = this;
			b.bind("submit", function() {
				var g = {};
				var f = $(this).formHash();
				var d = f["signin[domain_prefix]"];
				var c = {
					domain_prefix : d
				};
				try {
					a._datastore.store.saveAccount(c);
					window.location = "https://" + c.domain_prefix
							+ ".vendhq.com";
					return false
				} catch (h) {
					debug(h);
					return false
				}
			})
		}
	}
})();
var GeneralInterface = (function() {
	return {
		_datastore : {},
		init : function(b) {
			var a = this;
			a._datastore = Datastore;
			a._datastore.checkOfflineCapabilities(function() {
				if (a._datastore.offline_supported) {
					var c = {
						domain_prefix : b
					};
					try {
						a._datastore.store.saveAccount(c)
					} catch (d) {
					}
				}
			})
		}
	}
})();
UI.ImageWidget = (function() {
	$(function() {
		$(".form-image a.remove-image").live(
				"click",
				function() {
					$($(this).parents()[1]).find(".form-image-delete input")
							.each(function() {
								$(this).attr("checked", true)
							});
					$(this).closest(".input-row.contains-checkbox").fadeOut(
							"fast");
					$(this).parent().fadeOut("fast")
				})
	})
})();
UI.ImagePlaceholderUpdate = (function() {
	$(function() {
		$(".image-dynamic[data-placeholder=uploading]").each(function() {
			var b = $(this);
			var a = 5000;
			var d = function() {
				var h = b.data("id");
				var f = b.data("format");
				var e = b.data("subtype");
				var g = b.data("placeholder");
				if (g == "uploading") {
					$.ajax({
						url : "/image/" + e + "/" + h + "/status?format=" + f,
						dataType : "json",
						success : function(i) {
							if (!i.is_placeholder) {
								b.removeData("placeholder");
								b.removeAttr("data-placeholder");
								var j = b.clone(true);
								j.hide().load(function() {
									b.fadeOut();
									b.replaceWith(j);
									j.fadeIn()
								});
								j.appendTo(b.parent());
								j.attr("src", i.url)
							}
						}
					});
					c()
				}
			};
			var c = function() {
				setTimeout(d, a);
				a = a * 2
			};
			c()
		})
	})
})();
var QuickKeysSell = (function() {
	var s = JSON
			.parse('{"quick_keys":{"groups":[{"name":"Group 1","position":0,"pages":[{"page":1,"keys":[]}]}]}}'), y = null, v = null, o = null, c = null, m = null, E = false;
	var F = function() {
		l();
		A(d())
	};
	var g = function(G) {
		debug("Quick key pressed", G);
		b();
		m(G)
	};
	var p = function(H, I, G) {
		if (!G) {
			G = 0
		}
		$.each(H.options, function(J, K) {
			if (J > G) {
				I[K.label] = ""
			}
		})
	};
	var u = function(L) {
		var J = $(L).parents(".context-menu");
		var N = J.find(".key-variation-menu");
		var H = J.data("parent").data("key");
		var I = $(L).parents(".key-variation-menu").data("selections");
		var M = 0;
		var G = 0;
		$.each(H.options, function(O, P) {
			if (P.label == $(L).attr("name")) {
				G = M;
				p(H, I, M)
			}
			M++
		});
		I[$(L).attr("name")] = $(L).val();
		if (G == (H.options.length - 1) && $(L).val() !== "") {
			var K = H;
			$.each(H.variants, function(Q, P) {
				var O = true;
				$.each(H.options, function(R, S) {
					if (P[S.label] != I[S.label]) {
						O = false
					}
				});
				if (O) {
					K = P
				}
			});
			g(K)
		} else {
			j(H, I, N)
		}
	};
	var t = function(I) {
		var J = $("<div></div>");
		if (I.parent && I.variants.length > 1) {
			var H = $("<div></div>", {
				"class" : "key-variation-menu"
			}).appendTo(J);
			var G = [];
			$.each(I.selections, function(K, L) {
				G[K] = L
			});
			$(H).data("selections", G);
			j(I, G, H)
		}
		return J
	};
	var n = function(K, I) {
		var H = y.find(".context-menu");
		if (H.length) {
			k(H.data("parent"))
		}
		var J = parseInt(K.css("margin-left").replace("px", ""), 10);
		var M = K.offset();
		var G = K.height();
		var L = $(
				"<div></div>",
				{
					"class" : "context-menu radius-5",
					html : I,
					style : "left: "
							+ (M.left + (K.outerWidth() / 2) + (J / 2))
							+ "px; top: " + (M.top + G - 5) + "px;"
				}).appendTo(y);
		L.css("left", (L.offset().left - (L.outerWidth() / 2)) + "px");
		L.data("parent", K);
		K.data("window", true);
		K.data("window-obj", L);
		$("<span></span>", {
			"class" : "context-menu-arrow img-btn",
			style : "left: " + ((L.outerWidth() / 2) - (17)) + "px;"
		}).prependTo(L)
	};
	var k = function(G) {
		var H = G.data("window-obj");
		G.data("window", false);
		H.remove()
	};
	var b = function() {
		var G = y.find(".context-menu");
		if (G.length) {
			$.each(G, function(H, I) {
				k($(I).data("parent"))
			})
		}
	};
	var i = function(G) {
		var H = f();
		if (H.pages.length > G && G > -1) {
			b();
			o.find(".page.selected").removeClass("selected");
			o.find("ul.page[rel=" + G + "]").addClass("selected");
			c.find("a.selected").removeClass("selected");
			c.find("a[rel=" + G + "]").addClass("selected")
		}
	};
	var C = function() {
		var G = w();
		i((G + 1))
	};
	var r = function() {
		var G = w();
		i((G - 1))
	};
	var x = function(L, M, K, J) {
		var I = [];
		if (J === 0) {
			$.each(L.variants, function(P, O) {
				$.each(L.options, function(R, Q) {
					if (Q.label == K && I.indexOf(O[Q.label]) == -1) {
						I.push(O[Q.label])
					}
				})
			})
		} else {
			var H = [];
			var N = 0;
			$.each(L.options, function(O, P) {
				if (N < J) {
					if (M[P.label] !== "") {
						H[P.label] = M[P.label]
					}
				}
				N++
			});
			var G = z(L, H);
			$.each(G, function(P, O) {
				$.each(L.options, function(R, Q) {
					if (Q.label == K && I.indexOf(O[Q.label]) == -1) {
						I.push(O[Q.label])
					}
				})
			})
		}
		return I
	};
	var z = function(H, I) {
		var G = [];
		$.each(H.variants, function(K, L) {
			var J = true;
			$.each(H.options, function(M, N) {
				if (I[N.label] !== undefined) {
					if (L[N.label] != I[N.label]) {
						J = false
					}
				}
			});
			if (J) {
				G.push(L)
			}
		});
		return G
	};
	var B = function(H, G) {
		var I = 0;
		$.each(H.options, function(J, K) {
			if (K.label == G) {
				return I
			}
			I++
		});
		return -1
	};
	var w = function() {
		return parseInt(o.find("ul.page.selected").attr("rel"), 10)
	};
	var D = function() {
		return f().pages[o.find("ul.page.selected").attr("rel")]
	};
	var f = function() {
		return a($("#quick-keys #list ul:first li:first:not(.page)").attr(
				"class"))
	};
	var a = function(G) {
		var H = false;
		$.each(s.quick_keys.groups, function(I, J) {
			if (J.name == G) {
				H = J
			}
		});
		return H
	};
	var e = function(G) {
		var H = -1;
		$.each(s.quick_keys.groups, function(I, J) {
			if (J.name == G) {
				H = I
			}
		});
		return parseInt(H, 10)
	};
	var d = function() {
		return a(v.find("li:first a").attr("rel"))
	};
	var j = function(H, J, I) {
		I.html("");
		var G = -1;
		$.each(H.options, function(L, O) {
			var N = J[O.label];
			var K = $("<select></select>", {
				name : O.label
			}).appendTo(I);
			var M = x(H, J, O.label, L);
			if (N === "" && L > 0) {
				if (G < (L - 1)) {
					K.attr("disabled", "disabled")
				} else {
					K.focus().mousedown().click()
				}
			}
			if (N !== "") {
				G = L
			}
			$("<option></option>", {
				selected : (N === "") ? "selected" : "",
				value : "",
				html : O.label,
				"class" : "default"
			}).appendTo(K);
			$.each(M, function(Q, P) {
				$("<option></option>", {
					selected : (N == P) ? "selected" : "",
					value : P,
					html : P
				}).appendTo(K)
			})
		})
	};
	var l = function() {
		v.html("");
		var H = s.quick_keys.groups.length;
		var G = Math.floor(516 / H) - 1;
		var I = [];
		$.each(s.quick_keys.groups, function(J, K) {
			I[parseInt(K.position, 10)] = $("<li></li>", {
				html : $("<a></a>", {
					href : "#",
					rel : K.name,
					html : K.name,
					"class" : K.name + " parent",
					style : "width: " + G + "px;"
				})
			})
		});
		$.each(I, function(J, K) {
			v.append(K)
		})
	};
	var A = function(I, H) {
		b();
		if (!H) {
			H = 0
		}
		c.empty();
		o.empty();
		var G = $("<li></li>", {
			"class" : I.name
		}).appendTo(o);
		$.each(I.pages, function(L, K) {
			q(K, G, L, H);
			var J = $("<li></li>", {
				html : $("<a></a>", {
					html : (L + 1),
					rel : L,
					href : "#"
				})
			}).appendTo(c);
			if (L == H) {
				J.find("a").addClass("selected")
			}
		});
		v.find("li.selected").removeClass("selected");
		v.find("li a[rel=" + I.name + "]").parent().addClass("selected")
	};
	var q = function(K, L, J, H) {
		var G = $(
				'<ul class="page' + (J == H ? " selected" : "") + '" rel="' + J
						+ '"></ul>').appendTo(L), I = [];
		$.each(K.keys, function(M, N) {
			I[N.position] = h(N);
			I[N.position].data("key", N)
		});
		$.each(I, function(M, N) {
			G.append(N)
		});
		G.addTouch();
		G.disableSelection()
	};
	var h = function(G) {
		return $('<li class="' + G.color + '"><button>' + G.label
				+ "</button></li>")
	};
	return {
		reload : function(H) {
			if (H !== undefined && H.quick_keys !== undefined
					&& H.quick_keys.groups !== undefined
					&& H.quick_keys.groups.length) {
				var G = 0;
				$.each(H.quick_keys.groups, function(I, J) {
					$.each(J.pages, function(K, L) {
						if (L.keys !== undefined && L.keys.length > 0) {
							G++
						}
					})
				});
				if (G > 0) {
					s = H;
					F();
					y.removeClass("default")
				} else {
					E = true;
					y.addClass("default")
				}
			} else {
				E = true;
				y.addClass("default")
			}
		},
		init : function(I, J) {
			y = $("#quick-keys");
			v = y.find("#menu ul");
			o = y.find("#list ul");
			c = y.find("#pages ul");
			if (I !== undefined && I.quick_keys !== undefined
					&& I.quick_keys.groups !== undefined
					&& I.quick_keys.groups.length) {
				var G = 0;
				$.each(I.quick_keys.groups, function(K, L) {
					$.each(L.pages, function(M, N) {
						if (N.keys !== undefined && N.keys.length > 0) {
							G++
						}
					})
				});
				if (G > 0) {
					s = I;
					F()
				} else {
					E = true;
					y.addClass("default")
				}
			} else {
				E = true;
				y.addClass("default")
			}
			m = J;
			var H = this;
			$("#quick-keys .context-menu .key-variation-menu select").live(
					"change", function() {
						u($(this))
					});
			$("#menu ul li a").live(Register.TRIGGER, function() {
				var K = a($(this).attr("rel"));
				if (K) {
					A(K)
				}
				return false
			});
			$("#pages ul li a").live(Register.TRIGGER, function() {
				var K = parseInt($(this).html(), 10) - 1;
				i(K);
				return false
			});
			$("#pages a#page-previous").live(Register.TRIGGER, function() {
				r();
				return false
			});
			$("#pages a#page-next").live(Register.TRIGGER, function() {
				C();
				return false
			});
			$("#list ul.page li").live(Register.TRIGGER, function(N) {
				N.preventDefault();
				var P = $(this);
				if (P.data("window")) {
					k(P)
				} else {
					var K = P.data("key");
					if (K.parent !== undefined && K.parent && K.selections) {
						var Q = 0, O = false;
						$.each(K.selections, function(R, S) {
							if (S === "") {
								O = true
							}
							Q++
						});
						var M = 0;
						if (K.options.length === null) {
							if (K.options[0] !== undefined) {
								M++
							}
							if (K.options[1] !== undefined) {
								M++
							}
							if (K.options[2] !== undefined) {
								M++
							}
							if (K.options[3] !== undefined) {
								M++
							}
							if (K.options[4] !== undefined) {
								M++
							}
						} else {
							M = K.options.length
						}
						if (!O && Q == M) {
							var L = K;
							$.each(K.variants, function(T, S) {
								var R = true;
								$.each(K.options, function(U, V) {
									if (S[V.label] != K.selections[V.label]) {
										R = false
									}
								});
								if (R) {
									L = S
								}
							});
							g(L)
						} else {
							n($(this), t($(this).data("key")))
						}
					} else {
						g(K)
					}
				}
				return false
			})
		}
	}
})();
var clone = function(c) {
	if (c === null || typeof (c) !== "object") {
		return c
	}
	var a = new c.constructor();
	for ( var b in c) {
		if (c.hasOwnProperty(b)) {
			a[b] = clone(c[b])
		}
	}
	return a
};
var S4 = function() {
	return (((1 + Math.random()) * 65536) | 0).toString(16).substring(1)
};
var guid = function() {
	return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4()
			+ S4() + S4())
};
var randomCode = function(e) {
	var d = "abcdefghijklmnopqrstuvwxyz1234567890";
	var c = "";
	for ( var a = 0; a < e; a++) {
		var b = Math.floor(Math.random() * 36);
		c += d.charAt(b)
	}
	return c
};
var roundNumber = function(b, c) {
	c = c || 2;
	var a = Math.round(b * Math.pow(10, c)) / Math.pow(10, c);
	return a.toFixed(c)
};
$.fn.fadeThenSlideToggle = function(b, d, c) {
	var a = this;
	if (a.is(":hidden")) {
		return a.slideDown(b, d).fadeTo(b, 1, d, c)
	} else {
		return a.fadeTo(b, 0, d).slideUp(b, d, c)
	}
};
$.maxZIndex = $.fn.maxZIndex = function(b) {
	var a = this;
	var c = {
		inc : 10,
		group : "*"
	};
	$.extend(c, b);
	var d = 0;
	$(c.group).each(function() {
		var e = parseInt($(this).css("z-index"), 10);
		d = e > d ? e : d
	});
	if (!a.jQuery) {
		return d
	}
	return a.each(function() {
		d += c.inc;
		$(this).css("z-index", d)
	})
};
var dateFormat = function() {
	var a = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g, b = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[\-+]\d{4})?)\b/g, d = /[^\-+\dA-Z]/g, c = function(
			f, e) {
		f = String(f);
		e = e || 2;
		while (f.length < e) {
			f = "0" + f
		}
		return f
	};
	return function(i, v, q) {
		var g = dateFormat;
		if (arguments.length === 1
				&& Object.prototype.toString.call(i) === "[object String]"
				&& !/\d/.test(i)) {
			v = i;
			i = undefined
		}
		i = i ? new Date(i) : new Date();
		if (isNaN(i)) {
			throw SyntaxError("invalid date")
		}
		v = String(g.masks[v] || v || g.masks["default"]);
		if (v.slice(0, 4) === "UTC:") {
			v = v.slice(4);
			q = true
		}
		var t = q ? "getUTC" : "get", l = i[t + "Date"](), e = i[t + "Day"](), j = i[t
				+ "Month"](), p = i[t + "FullYear"](), r = i[t + "Hours"](), k = i[t
				+ "Minutes"](), u = i[t + "Seconds"](), n = i[t
				+ "Milliseconds"](), f = q ? 0 : i.getTimezoneOffset(), h = {
			d : l,
			dd : c(l),
			ddd : g.i18n.dayNames[e],
			dddd : g.i18n.dayNames[e + 7],
			m : j + 1,
			mm : c(j + 1),
			mmm : g.i18n.monthNames[j],
			mmmm : g.i18n.monthNames[j + 12],
			yy : String(p).slice(2),
			yyyy : p,
			h : r % 12 || 12,
			hh : c(r % 12 || 12),
			H : r,
			HH : c(r),
			M : k,
			MM : c(k),
			s : u,
			ss : c(u),
			l : c(n, 3),
			L : c(n > 99 ? Math.round(n / 10) : n),
			t : r < 12 ? "a" : "p",
			tt : r < 12 ? "am" : "pm",
			T : r < 12 ? "A" : "P",
			TT : r < 12 ? "AM" : "PM",
			Z : q ? "UTC" : (String(i).match(b) || [ "" ]).pop().replace(d, ""),
			o : (f > 0 ? "-" : "+")
					+ c(Math.floor(Math.abs(f) / 60) * 100 + Math.abs(f) % 60,
							4),
			S : [ "th", "st", "nd", "rd" ][l % 10 > 3 ? 0
					: (l % 100 - l % 10 !== 10) * l % 10]
		};
		return v.replace(a, function(m) {
			return m in h ? h[m] : m.slice(1, m.length - 1)
		})
	}
}();
dateFormat.masks = {
	"default" : "ddd mmm dd yyyy HH:MM:ss",
	shortDate : "m/d/yy",
	mediumDate : "mmm d, yyyy",
	longDate : "mmmm d, yyyy",
	fullDate : "dddd, mmmm d, yyyy",
	shortTime : "h:MM TT",
	mediumTime : "h:MM:ss TT",
	longTime : "h:MM:ss TT Z",
	isoDate : "yyyy-mm-dd",
	isoTime : "HH:MM:ss",
	isoDateTime : "yyyy-mm-dd'T'HH:MM:ss",
	isoUtcDateTime : "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};
dateFormat.i18n = {
	dayNames : [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sunday",
			"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ],
	monthNames : [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec", "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" ]
};
var _isInteger = function(c) {
	var b = "1234567890";
	for ( var a = 0; a < c.length; a++) {
		if (b.indexOf(c.charAt(a)) == -1) {
			return false
		}
	}
	return true
};
var _getInt = function(f, d, e, c) {
	for ( var a = c; a >= e; a--) {
		var b = f.substring(d, d + a);
		if (b.length < e) {
			return null
		}
		if (_isInteger(b)) {
			return b
		}
	}
	return null
};
var getDateFromFormat = function(A, r) {
	A = A + "";
	r = r + "";
	var z = 0;
	var m = 0;
	var t = "";
	var f = "";
	var w = "";
	var j, g;
	var b = new Date();
	var k = b.getYear();
	var v = b.getMonth() + 1;
	var u = 1;
	var d = b.getHours();
	var s = b.getMinutes();
	var p = b.getSeconds();
	var l = "";
	var q;
	while (m < r.length) {
		t = r.charAt(m);
		f = "";
		while ((r.charAt(m) == t) && (m < r.length)) {
			f += r.charAt(m++)
		}
		if (f == "yyyy" || f == "yy" || f == "y") {
			if (f == "yyyy") {
				j = 4;
				g = 4
			}
			if (f == "yy") {
				j = 2;
				g = 2
			}
			if (f == "y") {
				j = 2;
				g = 4
			}
			k = _getInt(A, z, j, g);
			if (k === null) {
				return 0
			}
			z += k.length;
			if (k.length == 2) {
				if (k > 70) {
					k = 1900 + (k - 0)
				} else {
					k = 2000 + (k - 0)
				}
			}
		} else {
			if (f == "MMM" || f == "NNN") {
				v = 0;
				var h = dateFormat.i18n.monthNames;
				for (q = 0; q < h.length; q++) {
					var e = h[q];
					if (A.substring(z, z + e.length).toLowerCase() == e
							.toLowerCase()) {
						if (f == "MMM" || (f == "NNN" && q > 11)) {
							v = q + 1;
							if (v > 12) {
								v -= 12
							}
							z += e.length;
							break
						}
					}
				}
				if ((v < 1) || (v > 12)) {
					return 0
				}
			} else {
				if (f == "EE" || f == "E") {
					var n = dateFormat.i18n.dayNames;
					for (q = 0; q < n.length; q++) {
						var o = n[q];
						if (A.substring(z, z + o.length).toLowerCase() == o
								.toLowerCase()) {
							z += o.length;
							break
						}
					}
				} else {
					if (f == "MM" || f == "M") {
						v = _getInt(A, z, f.length, 2);
						if (v === null || (v < 1) || (v > 12)) {
							return 0
						}
						z += v.length
					} else {
						if (f == "dd" || f == "d") {
							u = _getInt(A, z, f.length, 2);
							if (u === null || (u < 1) || (u > 31)) {
								return 0
							}
							z += u.length
						} else {
							if (f == "hh" || f == "h") {
								d = _getInt(A, z, f.length, 2);
								if (d === null || (d < 1) || (d > 12)) {
									return 0
								}
								z += d.length
							} else {
								if (f == "HH" || f == "H") {
									d = _getInt(A, z, f.length, 2);
									if (d === null || (d < 0) || (d > 23)) {
										return 0
									}
									z += d.length
								} else {
									if (f == "KK" || f == "K") {
										d = _getInt(A, z, f.length, 2);
										if (d === null || (d < 0) || (d > 11)) {
											return 0
										}
										z += d.length
									} else {
										if (f == "kk" || f == "k") {
											d = _getInt(A, z, f.length, 2);
											if (d === null || (d < 1)
													|| (d > 24)) {
												return 0
											}
											z += d.length;
											d--
										} else {
											if (f == "mm" || f == "m") {
												s = _getInt(A, z, f.length, 2);
												if (s === null || (s < 0)
														|| (s > 59)) {
													return 0
												}
												z += s.length
											} else {
												if (f == "ss" || f == "s") {
													p = _getInt(A, z, f.length,
															2);
													if (p === null || (p < 0)
															|| (p > 59)) {
														return 0
													}
													z += p.length
												} else {
													if (f == "a") {
														if (A.substring(z,
																z + 2)
																.toLowerCase() == "am") {
															l = "AM"
														} else {
															if (A
																	.substring(
																			z,
																			z + 2)
																	.toLowerCase() == "pm") {
																l = "PM"
															} else {
																return 0
															}
														}
														z += 2
													} else {
														if (A.substring(z, z
																+ f.length) != f) {
															return 0
														} else {
															z += f.length
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	if (z != A.length) {
		return 0
	}
	if (v == 2) {
		if (((k % 4 === 0) && (k % 100 !== 0)) || (k % 400 === 0)) {
			if (u > 29) {
				return 0
			}
		} else {
			if (u > 28) {
				return 0
			}
		}
	}
	if ((v == 4) || (v == 6) || (v == 9) || (v == 11)) {
		if (u > 30) {
			return 0
		}
	}
	if (d < 12 && l == "PM") {
		d = d - 0 + 12
	} else {
		if (d > 11 && l == "AM") {
			d -= 12
		}
	}
	var a = new Date(k, v - 1, u, d, s, p);
	return a.getTime()
};
Date.prototype.format = function(a, b) {
	return dateFormat(this, a, b)
};
Date.prototype.getUTCISODate = function() {
	var c = this.getTime() + (this.getTimezoneOffset() * 60000);
	var g = new Date(c);
	var a = g.getDate();
	var f = g.getMonth();
	f++;
	var b = g.getFullYear();
	var e = b + "-" + zeroPad(f) + "-" + zeroPad(a) + " "
			+ zeroPad(g.getHours()) + ":" + zeroPad(g.getMinutes()) + ":"
			+ zeroPad(g.getSeconds());
	return e
};
Date.prototype.getLocalISODate = function() {
	var e = new Date();
	var c = this.getTime() - (e.getTimezoneOffset() * 60000);
	var h = new Date(c);
	var a = h.getDate();
	var g = h.getMonth();
	g++;
	var b = h.getFullYear();
	var f = b + "-" + zeroPad(g) + "-" + zeroPad(a) + " "
			+ zeroPad(h.getHours()) + ":" + zeroPad(h.getMinutes()) + ":"
			+ zeroPad(h.getSeconds());
	return f
};
Date.prototype.getLocalHumanDate = function() {
	var b = new Date();
	var a = this.getTime() - (b.getTimezoneOffset() * 60000);
	var f = new Date(a);
	try {
		return dateFormat(f, "mmm d, yyyy HH:MM:ss")
	} catch (c) {
		return false
	}
};
var zeroPad = function(a) {
	if ("number" !== typeof a) {
		return a
	}
	if (a < 10) {
		if (a < 0 && a > -10) {
			a = "-0" + -a
		} else {
			if (a < -9) {
				return a
			} else {
				a = "0" + a
			}
		}
	}
	return a
};
$.fn.selectRange = function(b, a) {
	return this.each(function() {
		if (this.setSelectionRange) {
			this.focus();
			this.setSelectionRange(b, a)
		} else {
			if (this.createTextRange) {
				var c = this.createTextRange();
				c.collapse(true);
				c.moveEnd("character", a);
				c.moveStart("character", b);
				c.select()
			}
		}
	})
};
var isArray = ("isArray" in Array) ? Array.isArray : function(a) {
	return Object.prototype.toString.call(a) === "[object Array]"
};
var validEmailAddressPattern = /^(("[\w\-\+\s]+")|([\w\+\-]+(?:\.[\w\+\-]+)*)|("[\w\+\-\s]+")([\w\+\-]+(?:\.[\w\+\-]+)*))(@((?:[\w\-]+\.)*\w[\w\-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/;
var isValidEmailAddress = function(a) {
	if ("string" !== typeof a) {
		return false
	}
	return validEmailAddressPattern.test(a.toLowerCase())
};
var codeAddress = function(c, a) {
	var e = new google.maps.Geocoder();
	var f;
	var b;
	var d;
	e.geocode({
		address : c
	}, function(i, h) {
		if (h == google.maps.GeocoderStatus.OK) {
			f = new google.maps.LatLng(0, 0);
			b = {
				zoom : 12,
				center : f,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var j = new google.maps.Map(document.getElementById(a), b);
			j.setCenter(i[0].geometry.location);
			var g = new google.maps.Marker({
				map : j,
				position : i[0].geometry.location
			})
		} else {
			$("#" + a).hide()
		}
	})
};
var oc = function(b) {
	var d = {};
	for ( var c = 0; c < b.length; c++) {
		d[b[c]] = ""
	}
	return d
};
var simulateMouseEvent = function(b, c) {
	var a = document.createEvent("MouseEvents");
	a.initMouseEvent(b, true, true, window, 0, 0, 0, 0, 0, false, false, false,
			false, 0, null);
	c.dispatchEvent(a)
};
var var_dump = function() {
	var d = "", a = " ", c = 4, f = 0, h = 0, e, b, j, g;
	e = function(k) {
		var i = (/\W*function\s+([\w\$]+)\s*\(/).exec(k);
		if (!i) {
			return "(Anonymous)"
		}
		return i[1]
	};
	b = function(k, m) {
		var n = "";
		for ( var l = 0; l < k; l++) {
			n += m
		}
		return n
	};
	j = function(p, m) {
		var k = "";
		if (p === null) {
			k = "NULL"
		} else {
			if (typeof p === "boolean") {
				k = "bool(" + p + ")"
			} else {
				if (typeof p === "string") {
					k = "string(" + p.length + ') "' + p + '"'
				} else {
					if (typeof p === "number") {
						if (parseFloat(p) == parseInt(p, 10)) {
							k = "int(" + p + ")"
						} else {
							k = "float(" + p + ")"
						}
					} else {
						if (typeof p === "undefined") {
							k = "undefined"
						} else {
							if (typeof p === "function") {
								var o = p.toString().split("\n");
								k = "";
								for ( var l = 0, n = o.length; l < n; l++) {
									k += (l !== 0 ? "\n" + m : "") + o[l]
								}
							} else {
								if (p instanceof Date) {
									k = "Date(" + p + ")"
								} else {
									if (p instanceof RegExp) {
										k = "RegExp(" + p + ")"
									} else {
										if (p.nodeName) {
											switch (p.nodeType) {
											case 1:
												if (typeof p.namespaceURI === "undefined"
														|| p.namespaceURI === "http://www.w3.org/1999/xhtml") {
													k = 'HTMLElement("'
															+ p.nodeName + '")'
												} else {
													k = 'XML Element("'
															+ p.nodeName + '")'
												}
												break;
											case 2:
												k = "ATTRIBUTE_NODE("
														+ p.nodeName + ")";
												break;
											case 3:
												k = "TEXT_NODE(" + p.nodeValue
														+ ")";
												break;
											case 4:
												k = "CDATA_SECTION_NODE("
														+ p.nodeValue + ")";
												break;
											case 5:
												k = "ENTITY_REFERENCE_NODE";
												break;
											case 6:
												k = "ENTITY_NODE";
												break;
											case 7:
												k = "PROCESSING_INSTRUCTION_NODE("
														+ p.nodeName
														+ ":"
														+ p.nodeValue + ")";
												break;
											case 8:
												k = "COMMENT_NODE("
														+ p.nodeValue + ")";
												break;
											case 9:
												k = "DOCUMENT_NODE";
												break;
											case 10:
												k = "DOCUMENT_TYPE_NODE";
												break;
											case 11:
												k = "DOCUMENT_FRAGMENT_NODE";
												break;
											case 12:
												k = "NOTATION_NODE";
												break
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return k
	};
	g = function(p, o, n, i) {
		if (o > 0) {
			o++
		}
		var q = "", k = b(n * (o - 1), i), t = b(n * (o + 1), i), r = "", m = "";
		if (typeof p === "object" && p !== null) {
			if (p.constructor && e(p.constructor) === "PHPJS_Resource") {
				return p.var_dump()
			}
			f = 0;
			for (q in p) {
				f++
			}
			r += "array(" + f + ") {\n";
			for ( var s in p) {
				var l = p[s];
				if (typeof l === "object" && l !== null && !(l instanceof Date)
						&& !(l instanceof RegExp) && !l.nodeName) {
					r += t + "[" + s + "] =>\n" + t + g(l, o + 1, n, i)
				} else {
					m = j(l, t);
					r += t + "[" + s + "] =>\n" + t + m + "\n"
				}
			}
			r += k + "}\n"
		} else {
			r = j(p, t)
		}
		return r
	};
	d = g(arguments[0], 0, c, a);
	for (h = 1; h < arguments.length; h++) {
		d += "\n" + g(arguments[h], 0, c, a)
	}
	return d
};
var Is = {
	init : function(b) {
		var c = this.platforms, f = c.length, e, a;
		b = b || window.navigator;
		for (e = 0; e < f; e++) {
			a = c[e];
			this[a.identity] = a.regex.test(b[a.property])
		}
		this.Desktop = this.Mac || this.Windows
				|| (this.Linux && !this.Android);
		this.Tablet = this.iPad;
		this.Phone = !this.Desktop && !this.Tablet;
		this.iOS = this.iPhone || this.iPad || this.iPod;
		this.Standalone = !!window.navigator.standalone;
		var d = [];
		for (e in this) {
			if (this[e] === true) {
				d.push("is-" + e.toLowerCase())
			}
		}
		if (window.top !== window) {
			d.push("vend-chromeless")
		}
		$("html").addClass(d.join(" "))
	},
	platforms : [ {
		property : "platform",
		regex : /iPhone/i,
		identity : "iPhone"
	}, {
		property : "platform",
		regex : /iPod/i,
		identity : "iPod"
	}, {
		property : "userAgent",
		regex : /iPad/i,
		identity : "iPad"
	}, {
		property : "userAgent",
		regex : /Blackberry/i,
		identity : "Blackberry"
	}, {
		property : "userAgent",
		regex : /Android/i,
		identity : "Android"
	}, {
		property : "userAgent",
		regex : /com\.vendhq\.vend/i,
		identity : "NativeClient"
	}, {
		property : "platform",
		regex : /Mac/i,
		identity : "Mac"
	}, {
		property : "platform",
		regex : /Win/i,
		identity : "Windows"
	}, {
		property : "platform",
		regex : /Linux/i,
		identity : "Linux"
	} ]
};
Is.init();
var VendPOS = {
	util : {
		Tools : {
			_history : [],
			log : function() {
				console.log(arguments);
				VendPOS.util.Tools._history.push(arguments)
			},
			parseUrlParams : function(e) {
				var c = {}, b = e.replace(/^\?/, "").split("&"), a = b.length, d = 0, f;
				for (; d < a; d++) {
					if (!b[d]) {
						continue
					}
					f = b[d].split("=");
					c[f[0]] = f[1]
				}
				return c
			},
			parseUrl : function(c) {
				var b = document.createElement("a"), d;
				b.href = c;
				d = {
					source : c,
					protocol : b.protocol.replace(":", ""),
					host : b.hostname,
					port : b.port,
					query : b.search,
					params : VendPOS.util.Tools.parseUrlParams(b.search),
					file : (b.pathname.match(/\/([^\/?#]+)$/i) || [ "", "" ])[1],
					hash : b.hash.replace("#", ""),
					path : b.pathname.replace(/^([^\/])/, "/$1"),
					relative : (b.href.match(/tps?:\/\/[^\/]+(.+)/) || [ "", "" ])[1],
					segments : b.pathname.replace(/^\//, "").split("/")
				};
				b = undefined;
				return d
			}
		},
		NativeBridge : {
			_callbacks : {},
			_printQueue : {},
			_iframeLocation : undefined,
			_doCall : function(e, a, h, b) {
				var g = VendPOS.util.NativeBridge, f, d, c;
				if ("undefined" === typeof g._iframeLocation) {
					return
				}
				d = (Array.isArray(a) && a.length) ? "?"
						+ encodeURIComponent(JSON.stringify(a)) : "";
				if ("function" === typeof h) {
					f = guid();
					g._callbacks[f] = {
						callback : h,
						scope : b || window
					};
					c = "vend-nativebridge://" + e + "/" + f + d
				} else {
					c = "vend-nativebridge://" + e + d
				}
				g._iframeLocation.replace(c)
			},
			initialise : function() {
				var b = document.createElement("iframe"), a = window.localStorage;
				$.ajaxSetup({
					error : function(f, e, d, c) {
						VendPOS.util.NativeBridge.requireUser(function() {
							$.ajax(e)
						})
					}
				});
				b.setAttribute("style", "display: none;");
				document.documentElement.appendChild(b);
				VendPOS.util.NativeBridge._iframeLocation = b.contentWindow.location;
				$.ajax({
					url : "/api/outlets",
					dataType : "json",
					success : function(c) {
						a.setItem("outlets", JSON.stringify(c.outlets))
					}
				});
				$.ajax({
					url : "/api/users/authenticated",
					dataType : "json",
					success : function(c) {
						a.setItem("users", JSON.stringify(c.users))
					}
				});
				b = undefined
			},
			print : function(g, j, k) {
				var a = guid(), c = VendPOS.util.NativeBridge, h = $(
						'link[media="print"]', document.head), d = $('<div id="print-wrapper">'
						+ g + "</div>"), f, e, b;
				$("#customer-receipt", d).css({
					margin : 0,
					width : "auto"
				});
				$("#receipt-header img", d).css({
					width : "auto"
				});
				f = "<!DOCTYPE html><html><head>";
				for (e = 0, b = h.length; e < b; e++) {
					f += '<link rel="stylesheet" href="' + h[e].href + '" />'
				}
				f += '</head><body id="register"><div id="receipt">' + d.html()
						+ "</div></body></html>";
				c._printQueue[a] = f;
				c._doCall("print", [ a ], j, k)
			},
			setNavigation : function(b, c, a) {
				VendPOS.util.NativeBridge._doCall("setNavigation", [ b ], c, a)
			},
			setState : function(b, c, a) {
				VendPOS.util.NativeBridge._doCall("setState", [ b ], c, a)
			},
			requireRegister : function(b, a) {
				VendPOS.util.NativeBridge._doCall("requireRegister", [], b, a)
			},
			requireUser : function(b, a) {
				VendPOS.util.NativeBridge._doCall("requireUser", [], b, a)
			},
			setCheckedInCount : function(b, c, a) {
				VendPOS.util.NativeBridge._doCall("setCheckedInCount", [ b ],
						c, a)
			},
			openCashDrawer : function(b, a) {
				VendPOS.util.NativeBridge._doCall("openCashDrawer", [], b, a)
			}
		},
		WebBridge : {
			getPrintItem : function(c) {
				var a = VendPOS.util.NativeBridge._printQueue, b = a[c];
				if ("undefined" === typeof b) {
					return ""
				}
				a[c] = undefined;
				delete a[c];
				return b
			},
			getUsers : function() {
				var a = window.localStorage;
				return a.hasOwnProperty("users") ? a.getItem("users") : "[]"
			},
			refreshUsers : function() {
				var a = window.localStorage;
				$.ajax({
					url : "/api/users/authenticated",
					dataType : "json",
					success : function(b) {
						a.setItem("users", JSON.stringify(b.users))
					}
				})
			},
			setUser : function(b) {
				var a = this;
				$.ajax({
					url : "/api/users/current",
					type : "POST",
					data : {
						id : b
					},
					dataType : "json",
					success : function(c) {
						a.refreshUsers()
					}
				})
			},
			getRegisters : function() {
				var g = window.localStorage, k = Datastore.config.register_id, c = g
						.hasOwnProperty("registers") ? JSON.parse(g
						.getItem("registers")) : [], d = g
						.hasOwnProperty("outlets") ? JSON.parse(g
						.getItem("outlets")) : [], e = [], f = {}, h, j, b, a;
				for (b = 0, a = d.length; b < a; b = b + 1) {
					j = d[b];
					if ("undefined" !== typeof f[j.id]) {
						continue
					}
					f[j.id] = j
				}
				for (b = 0, a = c.length; b < a; b = b + 1) {
					h = c[b];
					h.outlet = null;
					h.outlet_name = null;
					h.active = false;
					if (k === h.id) {
						h.active = true
					}
					j = f[h.outlet_id];
					if ("undefined" !== typeof j) {
						h.outlet = j;
						h.outlet_name = j.name
					}
					e.push(h)
				}
				return JSON.stringify(e)
			},
			getRegister : function() {
				var b = JSON.parse(VendPOS.util.WebBridge.getRegisters()), a = b.length, c = 0, d;
				for (; c < a; c = c + 1) {
					d = b[c];
					if (d.active) {
						return JSON.stringify(d)
					}
				}
				return "null"
			},
			getOutlets : function() {
				var h = window.localStorage, m = Datastore.config.register_id, c = h
						.hasOwnProperty("registers") ? JSON.parse(h
						.getItem("registers")) : [], d = h
						.hasOwnProperty("outlets") ? JSON.parse(h
						.getItem("outlets")) : [], g = [], j, k, b, a, f, e;
				for (b = 0, a = d.length; b < a; b = b + 1) {
					k = d[b];
					k.active = false;
					k.registers = [];
					for (f = 0, e = c.length; f < e; f = f + 1) {
						j = c[f];
						j.active = false;
						if (m === j.id) {
							j.active = true;
							k.active = true
						}
						if (k.id === j.outlet_id) {
							k.registers.push(j)
						}
					}
					g.push(k)
				}
				return JSON.stringify(g)
			},
			setRegister : function(c) {
				var a = window.localStorage, b;
				Datastore.config.register_id = c;
				b = JSON.parse(VendPOS.util.WebBridge.getRegister());
				Datastore.register = b;
				if ("undefined" !== typeof b.outlet) {
					Datastore.config.register = b;
					Datastore.config.outlet_id = b.outlet.id;
					Datastore.config.outlet_name = b.outlet.name
				}
				a.removeItem("config");
				a.setItem("config", JSON.stringify(Datastore.config));
				window.location.reload()
			},
			addProduct : function(a) {
				$("#product_search_product_sku").val(a);
				$("#register-input").triggerHandler("submit")
			},
			setNavigation : function(a) {
				switch (a) {
				case "current":
					Register.showCurrentSale.call(Register);
					break;
				case "retrieve":
					Register.openSavedSale.call(Register);
					break;
				case "close":
					Register.closeRegister.call(Register);
					break
				}
			},
			signOut : function() {
				$.ajax({
					url : "/api/logout",
					dataType : "json"
				})
			},
			doSync : function() {
				Datastore.refreshLocalDataStore()
			},
			returnUrlReceived : function(a) {
				var b = VendPOS.util.Tools.parseUrl("http://" + a.substr(7));
				b.protocol = "vend";
				b.source = a;
				$(VendPOS.util.WebBridge).trigger("url:" + b.host, b)
			},
			showCheckins : function() {
				$(window).triggerHandler("showCheckIns")
			},
			hideCheckins : function() {
				$(window).triggerHandler("hideCheckIns")
			},
			print : function() {
				VendPOS.NativeBridge.print($("#receipt").html())
			},
			triggerCallback : function(e, a) {
				var d = VendPOS.util.NativeBridge, c = d._callbacks[e];
				if ("undefined" === typeof c) {
					return
				}
				try {
					c.callback.apply(c.scope, a);
					d._callbacks[e] = undefined;
					delete d._callbacks[e]
				} catch (b) {
					console.log("NativeBridge Result Error", b, a, c.callback)
				}
				c = undefined
			}
		}
	}
};
VendPOS.NativeBridge = VendPOS.util.NativeBridge;
VendPOS.WebBridge = VendPOS.util.WebBridge;
if (Is.NativeClient) {
	VendPOS.util.NativeBridge.initialise()
};