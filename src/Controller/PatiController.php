<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

class PatiController extends AbstractController
{
    /**
     * @IsGranted("ROLE_PATINET")
     * @Route("/pati", name="pati")
     */
    public function index(): Response
    {
        return $this->render('/frontP.html.twig', [
            'controller_name' => 'PatiController',
        ]);
    }
}
