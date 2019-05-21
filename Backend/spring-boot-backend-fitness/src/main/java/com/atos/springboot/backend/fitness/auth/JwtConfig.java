package com.atos.springboot.backend.fitness.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEA0aL00Hbr+pEzbXoOsygzkJ5Rm5DA4SOkYLv7EgHf1GMR3k5B\r\n" + 
			"psAOYUA2rFNuRgSVQhR0cncM7xn1lDHcDwbzR/ZVsbTdAwQj3nK8NUSj8mUtXz8x\r\n" + 
			"IfKURyM9hifU1xGldRTmiYiXMnNgLgI8mlhrQCX0G2e8EjVm3pxdKoKBsDSID9Oy\r\n" + 
			"Hri8w/zAOlFCHvnbTq6p1cQLRRWcoFzMgSuQgTSnv3XUciLIrOn4vYLt7QZMnXAL\r\n" + 
			"mDYhjHXEES3f8tLaARtgay2A058Jn7tJ7fdbLQKSvwyTV1HzsG8WFIxbV1KqhAAf\r\n" + 
			"eguGOO7S5K2Tn8ewwQjoWiArbHwWGaJ8ja5YEQIDAQABAoIBACi1EgDg9tXDGE9r\r\n" + 
			"4irb5ZdxhrtEko+lUigOmKRYhNRDIJcgbxMoPCKaS4OO99zUyv735dzMTVCoo81R\r\n" + 
			"GUA5w0f4B4/oZPpO9YAem3lkiMlq6DKUnL/Q0QY3S50fYu8YWB6yHkoaofGEAest\r\n" + 
			"OANoo/ri49HmhNisUftsNZMpoHFMh8rnlr6RrBgzMHBbRjBNJjvNSP9C+c22GPix\r\n" + 
			"lMmNjdmotIrJNHD9iWU8+bSWb/JDGmAmWi/cpNZftDxyTfCj/OAF2tQqC0ASWVff\r\n" + 
			"aKUMr8uH2MYiDnT8E2b6jHdnkH7yFogGocSiTmHNxu6MABv4I5ugMRA7IIsA+2hy\r\n" + 
			"XgdFQqECgYEA/FNRT7SDZNZPqcgHIDL50ot+HO86O38JlYtZoj4HsGlPyIeEoRph\r\n" + 
			"9UgFNrEa9ePUygSdKzuOHVIg2zjT+yrXo63/ZxQXBy8jnMK1RBSVXODCN1EHEOiT\r\n" + 
			"hemjh6+Q37J2gvjsBDRuUSEcpdxzQHUQrg8S61QVNAljBQimSKWGzw0CgYEA1LB+\r\n" + 
			"AZJkcUcMEGFlzTNYiQE87QuiIYMuscXnaj/f2qM1VUVs2dJDDQgmZguZPjpL2YOv\r\n" + 
			"3gYuBGiWmNwU96vmgI22prO51M6naUc48mhXt5CIhz5URebeH9MlxNSBqSMEhK3v\r\n" + 
			"0iTSskEi/Qc0CQ8cPoaYHKNO51TnxgD6JC7lzBUCgYEA9sUNOqRR9GZcFrGXtnfk\r\n" + 
			"N3NQ7T9Rdipx1x0nljKQFomIC5ftsBk7QEUlRC3FLkGZM9F7XHKsNCTEGWHkfzmR\r\n" + 
			"F3Tt2YOeWdpSO9d4q5Jt2R2/E5ZdEL5EuKycz/B2ATBqEwEa4andUd7OQ6/rz1gt\r\n" + 
			"7ey0Fg5vhiq/+oZRCa9IvmkCgYAzUls1eeOuydwJX9c2yrsEZD4VXQdEWuk7kGuk\r\n" + 
			"4cDceyF1cAkyDmCLlnndidLyhxKia/pV8tvLyIj+XazZjqtSPd/jHd8rkKoo5zzA\r\n" + 
			"kZkI/GFVcR7YtzrGJycn9bdL3t1KPsZtyODmjn0o3UIbJYBFPikaT/XMMFq1FqtF\r\n" + 
			"a+ObeQKBgFsyiJLj4M+JSis+Y273OfA/jeXDW+/a7D/UC46vSvuSO0fkoEcZwEEJ\r\n" + 
			"seWtgiIvdaiNtcyNBBp3nkg50VsdVytGTlYWhxMeFmxpDnEcv4RWQmhTq5CLaaj0\r\n" + 
			"ddXbmgK6m7XR0EzfK55nY+mMMIbrS0ucxIzICrkjmYEJDpDGRSsv\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0aL00Hbr+pEzbXoOsygz\r\n" + 
			"kJ5Rm5DA4SOkYLv7EgHf1GMR3k5BpsAOYUA2rFNuRgSVQhR0cncM7xn1lDHcDwbz\r\n" + 
			"R/ZVsbTdAwQj3nK8NUSj8mUtXz8xIfKURyM9hifU1xGldRTmiYiXMnNgLgI8mlhr\r\n" + 
			"QCX0G2e8EjVm3pxdKoKBsDSID9OyHri8w/zAOlFCHvnbTq6p1cQLRRWcoFzMgSuQ\r\n" + 
			"gTSnv3XUciLIrOn4vYLt7QZMnXALmDYhjHXEES3f8tLaARtgay2A058Jn7tJ7fdb\r\n" + 
			"LQKSvwyTV1HzsG8WFIxbV1KqhAAfeguGOO7S5K2Tn8ewwQjoWiArbHwWGaJ8ja5Y\r\n" + 
			"EQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
